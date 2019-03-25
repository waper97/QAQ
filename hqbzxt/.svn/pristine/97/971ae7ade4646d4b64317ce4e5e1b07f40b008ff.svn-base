package com.yj.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.*;
import java.io.OutputStream;
import java.util.*;
import javax.imageio.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: KaptchaController.java
 * @Package com.yj.common.controller
 * @Description: TODO
 * @author xx
 * @date 2019-3-14
 */
@Controller
public class KaptchaController extends BaseController {

	@RequestMapping("/kaptcha")
	public void createKaptcha(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String abc = "0123456789";
			int length = abc.length();
			int width = 100, height = 40;
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			OutputStream os = response.getOutputStream();
			Graphics g = image.getGraphics();
			Random random = new Random();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);

			// g.setFont(new Font("Times New Roman",Font.PLAIN,28));
			g.setFont(new Font("黑体", Font.BOLD, 30));

			for (int i = 0; i < 8; i++) {

				g.setColor(new Color(random.nextInt(256), random.nextInt(256),
						random.nextInt(256)));
				// 画线
				g.drawLine(random.nextInt(150), random.nextInt(30),
						random.nextInt(150), random.nextInt(30));

			}

			String sRand = "";
			for (int i = 0; i < 4; i++) {
				int no = random.nextInt(length);
				String rand = abc.substring(no, no + 1);
				sRand += rand;
				g.setColor(new Color(random.nextInt(150), random.nextInt(200),
						random.nextInt(250)));
				g.drawString(rand,(i * 20) + 10, 30+random.nextInt(5));
			}
			getRequest().getSession()
					.setAttribute("KAPTCHA_SESSION_KEY", sRand);
			g.dispose();

			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
			os = null;
			response.flushBuffer();
		} catch (Exception e) {
		}
	}

}
