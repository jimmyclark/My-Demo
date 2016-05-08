uniform mat4 uMVPMatrix;//总变换矩阵
attribute vec3 aPosition;//顶点位置
attribute vec4 aColor;//顶点颜色
varying  vec4 vColor;//用于传给片元着色器的易变变量

void main()     {
   gl_Position = uMVPMatrix * vec4(aPosition,1);//根据总变换矩阵计算此次绘制此顶点的位置
   vColor = aColor;//接收的顶点颜色传递给片元着色器
}                      