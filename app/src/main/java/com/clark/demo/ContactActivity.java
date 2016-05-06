package com.clark.demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ClarkWu on 2016/4/28.
 */
public class ContactActivity extends Activity{
    private EditText messageText;
    private Button sendMessageToSMS;
    private Button showAllContactors;
    private ListView messageListView;
    private BaseAdapter adapter;
    private List<String> nameNumbers = new ArrayList<String>();
    private HashMap<String,String> nameNumberMap = new HashMap<String,String>();
    private static final String CONTACT_LOG = "CONTACT_LOG";
    private static final int SEND_MESSAGE_RETURN = 0x00001;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        messageText = (EditText) findViewById(R.id.contents);
        sendMessageToSMS = (Button) findViewById(R.id.btn_startContactToSendMessage);
        showAllContactors = (Button) findViewById(R.id.btn_readContactList);

        sendMessageToSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToSMS(messageText.getText().toString(), "");
            }
        });

        showAllContactors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllContactor();
            }
        });
    }

    private void showAllContactor() {
        if(nameNumbers.size()<=0) {
            getPhoneContactNumber();
        }
//
        TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        int absent = manager.getSimState();
        if(absent != TelephonyManager.SIM_STATE_ABSENT){
            getSIMContactNumber();
        }

        showListView();
    }

    private void getSIMContactNumber() {
        Uri uri = Uri.parse("content://icc/adn");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if(cursor == null) return;
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Intents.Insert.NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.NUMBER));
            if (TextUtils.isEmpty(number)) continue;
            number = filter(number);
            if(number == null) continue;
            nameNumberMap.put(number, name);
        }
        cursor.close();

        Set set = nameNumberMap.keySet();
        nameNumbers.clear();
        for(Iterator iter= set.iterator();iter.hasNext();){
            String phonesNumber = (String)iter.next();
            nameNumbers.add(phonesNumber);
        }
    }

    private void getPhoneContactNumber(){
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if(cursor == null) return;
        while(cursor.moveToNext()){
            int nameIndex = cursor.getColumnIndex((ContactsContract.PhoneLookup.DISPLAY_NAME));
            String name = cursor.getString(nameIndex);

            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,null,null);
            while(phone.moveToNext()){
                String number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if (TextUtils.isEmpty(number)) continue;
                number = filter(number);
                if(number == null) continue;
                nameNumberMap.put(number, name);
            }
            phone.close();
        }
        Set set = nameNumberMap.keySet();
        for(Iterator iter= set.iterator();iter.hasNext();){
            String phonesNumber = (String)iter.next();
            nameNumbers.add(phonesNumber);
        }
        cursor.close();
    }

    /**
     * 过滤号码
     * @number
     * @return
     */
    private String filter(String number) {
        if(number == null || number.trim().length() == 0) return number;
       number = number.replaceAll("[`~\\s*!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——|{}【】‘；：”“’。，、？|-]", "");
        if(number.matches("^1[3-8]\\d{9}$")) return number;
        if(number.matches("^861[3-8]\\d{9}$")){
            number = number.substring(2);
            return number;
        }else if(number.matches("^0861[3-8]\\d{9}$")){
            number = number.substring(3);
            return number;
        }else if(number.matches("^179511[3-8]\\d{9}$") || number.matches("^125931[3-8]\\d{9}$")){
            number = number.substring(5);
            return number;
        }
        return null;

    }

    private void showListView() {
        if(adapter != null){
            adapter.notifyDataSetChanged();
            return;
        }
        messageListView = (ListView) findViewById(R.id.lv_contacts);

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendMessageToSMS(messageText.getText().toString(),nameNumbers.get(position));
            }
        });

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return nameNumbers.size();
            }

            @Override
            public Object getItem(int position) {
                return nameNumbers.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MyViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(ContactActivity.this, R.layout.contact_list, null);
                    holder = new MyViewHolder();
                    holder.name = (TextView) convertView.findViewById(R.id.tv_name);
                    holder.telephone = (TextView) convertView.findViewById(R.id.tv_telephone);
                    convertView.setTag(holder);
                } else {
                    holder = (MyViewHolder) convertView.getTag();
                }
                holder.name.setText(nameNumberMap.get(nameNumbers.get(position)));
                holder.telephone.setText(nameNumbers.get(position));
                return convertView;
            }
        };
        messageListView.setAdapter(adapter);
    }

    static class MyViewHolder{
        public TextView name;
        public TextView telephone;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(CONTACT_LOG, "requestCode" + requestCode + "data:");
    }

    private boolean sendMessageToSMS(String message,String number) {
        if(message.trim().length() <= 0) return false;
        Uri uri=Uri.parse("smsto:"+ number);
        Intent intent=new Intent(Intent.ACTION_SENDTO,uri);//绿色文字就是启动发送短信窗口
        intent.putExtra("sms_body", message);
        startActivityForResult(intent, SEND_MESSAGE_RETURN);
        return true;
    }
}
