<%@ page  import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" %>
<%@ page import="java.io.OutputStream" %>
<%
try{
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
String abc = "0123456789";
int length = abc.length();
int width=150, height=30;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
OutputStream os=response.getOutputStream();
Graphics g = image.getGraphics();
Random random = new Random();
g.setColor(Color.WHITE);
g.fillRect(0, 0, width, height);

//g.setFont(new Font("Times New Roman",Font.PLAIN,28));
g.setFont(new Font("黑体", Font.BOLD, 30));
	
for(int i=0;i<8;i++){
    
    g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
//画线
    g.drawLine(random.nextInt(150), random.nextInt(30), random.nextInt(150), random.nextInt(30));

}

String sRand="";
for (int i=0;i<4;i++){
	int no = random.nextInt(length);
	String rand = abc.substring(no,no+1);
	sRand+=rand;
	g.setColor(new Color(random.nextInt(150),random.nextInt(200),random.nextInt(250)));
	g.drawString(rand,(i * 25) + 20, 21+random.nextInt(5));
}

session.setAttribute("KAPTCHA_SESSION_KEY",sRand);
g.dispose();

ImageIO.write(image, "JPEG",os);
os.flush();
os.close();
os=null;
response.flushBuffer();
out.clear();
out = pageContext.pushBody();
}
catch(IllegalStateException e)
{
}%>
