package cn.bluemobi.util.authCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidateCode {
	/** 验证码图片的宽度 */
	private int width;

	/** 验证码图片的高度 */
	private int height;
	
	/**
	 * 验证码的个数
	 */
	private int codeCount;

	/** 验证码 */
	private String codeStr;

	/** 验证图片 */
	private BufferedImage buffImg;

	public ValidateCode(int width, int height, int codeCount) {
		this.width = width;
		this.height = height;
		this.codeCount=codeCount;
		initCode();
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 生成验证图片和验证码
	 */
	private void initCode() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		String sRand = "";
		for (int i = 0; i < codeCount; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}
		codeStr = sRand;
		buffImg = image;
	}

	/**
	 * 获得验证码图片
	 * 
	 * @return 验证码图片
	 */
	public BufferedImage getImage() {
		return buffImg;
	}

	/**
	 * 获得验证码
	 * 
	 * @return 验证码
	 */
	public String getCode() {
		return codeStr;
	}

}
