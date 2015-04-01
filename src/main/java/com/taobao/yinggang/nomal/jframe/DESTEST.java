/*
 * Copyright (c) 2011 by Daniel Zhang, all rights reserved.
 */

package com.taobao.yinggang.nomal.jframe;

/**
 *
 * @author 寮犲箍
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

//鐩稿叧鏁版嵁褰曞叆
public class DESTEST extends JFrame {
	private int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6,
			64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37,
			29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };

	private int[] IP1 = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30,
			37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10,
			50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };

	private int[] E = { 32, 1, 2, 3, 4,
			5, // 镓╁厖缃崲锛屾墿鍏汇
			4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24,
			25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };

	private int[] P = { 16, 7, 20, 21, 29, 12, 28, 17, // 缃崲鍑芥暟P
			1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };

	private int[][] S = {
			{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0,
					7, // s鐩?
					0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3,
					10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 },

			{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11,
					5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 0, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12,
					0, 5, 14, 9 },

			{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15,
					1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3,
					11, 5, 2, 12 },

			{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,
					9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11,
					12, 7, 2, 14 },

			{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8,
					6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9,
					10, 4, 5, 3 },

			{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3,
					8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7,
					6, 0, 8, 13 },

			{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8,
					6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15,
					14, 2, 3, 12 },

			{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9,
					2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0,
					3, 5, 6, 11 } };

	private int[] change1 = { 57, 49, 41, 33, 25, 17,
			9, // 缃崲阃夋嫨1
			1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23,
			15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };

	private int[] change2 = { 14, 17, 11, 24, 1, 5, 3,
			28, // 缃崲阃夋嫨2
			15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48,
			44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };

	private int[] leftShift = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 }; // 宸︾Щ浣嶆暟

	boolean[] binary = new boolean[64]; // 浜岃繘鍒惰〃绀烘槑鏂?

	boolean[] result = new boolean[64]; // 浜岃繘鍒跺瘑鏂囩粨鏋?

	boolean[] key = new boolean[64]; // 浜岃繘鍒跺瘑阍ワ纸64浣嶏级

	boolean[] nextKey = new boolean[56]; // 浜岃繘鍒跺瘑阍ワ纸56浣嶏级

	boolean[] subKey = new boolean[768]; // 瀛桦偍浜х敓镄勫瓙瀵嗛挜鐢ㄤ簬瑙ｅ瘑

	boolean type = true; // true琛ㄧず鍗佸叚杩涘埗

	// false琛ㄧずascii镰?

	JTextField keyField = new JTextField(16);

	JTextArea plainArea = new JTextArea();

	JTextArea cipherArea = new JTextArea();

	JTextArea showArea = new JTextArea();

	JScrollPane plainPane = new JScrollPane(plainArea, // 婊氩姩鏉?
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JScrollPane cipherPane = new JScrollPane(cipherArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JScrollPane showPane = new JScrollPane(showArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	private JButton encode = new JButton("锷犲瘑"); // 澹版槑鎸夐挳

	private JButton decode = new JButton("瑙ｅ瘑");

	private ButtonGroup typeChoose = new ButtonGroup();

	JRadioButton hex = new JRadioButton("鍗佸叚杩涘埗");

	JRadioButton asc = new JRadioButton("ASCII镰");

	public DESTEST() {
		super("DES锷犺В瀵");
		setUpComponent(); // 鐣岄溃甯冨眬
		setUpEventListener(); // 浜嬩欢澶勭悊
		setVisible(true);
	}

	// 鐣岄溃璁剧疆鍑芥暟
	private void setUpComponent() {
		setSize(640, 480);

		plainArea.setLineWrap(true); // 璁剧疆镊姩鎹㈣
		cipherArea.setLineWrap(true);
		showArea.setLineWrap(true);

		hex.setSelected(true);
		asc.setSelected(false);
		typeChoose.add(hex);
		typeChoose.add(asc);

		JLabel l1 = new JLabel("瀵嗛挜");
		JLabel l2 = new JLabel("鏄庢枃");
		JLabel l3 = new JLabel("瀵嗘枃");
		JLabel l4 = new JLabel("瑙ｅ瘑缁撴灉");

		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new BorderLayout());
		keyPanel.add(l1, BorderLayout.LINE_START);
		keyPanel.add(keyField, BorderLayout.CENTER);

		JPanel plainPanel = new JPanel();
		plainPanel.setLayout(new BorderLayout());
		plainPanel.add(l2, BorderLayout.LINE_START);
		plainPanel.add(plainPane, BorderLayout.CENTER);
		plainPanel.add(encode, BorderLayout.LINE_END);

		JPanel groupPanel = new JPanel();
		groupPanel.setLayout(new FlowLayout());
		groupPanel.add(hex);
		groupPanel.add(asc);
		plainPanel.add(groupPanel, BorderLayout.SOUTH);

		JPanel cipherPanel = new JPanel();
		cipherPanel.setLayout(new BorderLayout());
		cipherPanel.add(l3, BorderLayout.NORTH);
		cipherPanel.add(cipherPane, BorderLayout.CENTER);
		cipherPanel.add(decode, BorderLayout.LINE_END);

		JPanel showPanel = new JPanel();
		showPanel.setLayout(new BorderLayout());
		showPanel.add(l4, BorderLayout.NORTH);
		showPanel.add(showPane, BorderLayout.CENTER);

		Container mainContainer = getContentPane();

		mainContainer.setLayout(new GridLayout(4, 1));
		mainContainer.add(keyPanel);
		mainContainer.add(plainPanel);
		mainContainer.add(cipherPanel);
		mainContainer.add(showPanel);
	}

	// 鐩戝惉鍣ㄨ缃嚱鏁?
	private void setUpEventListener() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		encode.addActionListener( // 锷犲瘑鎸夐挳鐩戝惉
		new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				cipherArea.setText("");
				textToBit(key, keyField.getText(), true); // 鍙栧缑瀵嗛挜
				splitPassage(plainArea, binary, type, true);// 鍒嗗壊鍏ㄦ枃鍒嗗埆锷犲瘑
			}
		});

		decode.addActionListener( // 瑙ｅ瘑鎸夐挳鐩戝惉
		new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				showArea.setText("");
				textToBit(key, keyField.getText(), true);
				splitPassage(cipherArea, result, type, false);
			} // 鍒嗗壊鍏ㄦ枃鍒嗗埆瑙ｅ瘑
		});

		hex.addActionListener( // radiobutton鐩戝惉(16杩涘埗)
		new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (hex.isSelected() == true)
					type = true;
			}
		});

		asc.addActionListener( // radiobutton鐩戝惉(asc镰?
		new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (asc.isSelected() == true)
					type = false;
			}
		});
	}

	// 鍒嗗壊鍏ㄦ枃鍒嗘锷犲瘑鍑芥暟
	private void splitPassage(JTextArea area, boolean[] splitedBit, boolean type, boolean choose) {
		String text = area.getText();
		if (text.length() % 16 != 0) {
			int lack = 16 - text.length() % 16;
			for (int i = 0; i < lack; i++)
				text = text + " ";
		}

		if (type == true) {
			for (int i = 0; i < text.length(); i = i + 16) {
				textToBit(splitedBit, text.substring(i, i + 16), type);
				DES(choose);
			}
		}
		if (type == false) {
			for (int i = 0; i < text.length(); i = i + 8) {
				textToBit(splitedBit, text.substring(i, i + 8), type);
				DES(choose);
			}
		}
	}

	// 灏嗗瓧绗︿覆杞寲涓篵oolean鏁扮粍浠ヨ〃绀轰簩杩涘埗锛宼ype琛ㄧず16杩涘埗鎴朼sc镰佺殑阃夋嫨
	private void textToBit(boolean[] out, String in, boolean type) {
		if (type == true) {
			int tempIn = 0;
			for (int i = 0; i < in.length(); i++) {
				if (in.charAt(i) < '0' || in.charAt(i) > '9') {
					if (in.charAt(i) == 'a' || in.charAt(i) == 'A')
						tempIn = 10;
					if (in.charAt(i) == 'b' || in.charAt(i) == 'B')
						tempIn = 11;
					if (in.charAt(i) == 'c' || in.charAt(i) == 'C')
						tempIn = 12;
					if (in.charAt(i) == 'd' || in.charAt(i) == 'D')
						tempIn = 13;
					if (in.charAt(i) == 'e' || in.charAt(i) == 'E')
						tempIn = 14;
					if (in.charAt(i) == 'f' || in.charAt(i) == 'F')
						tempIn = 15;
				} else
					tempIn = in.charAt(i) - 48;

				for (int j = 3; j >= 0; j--) {
					if (tempIn % 2 == 1)
						out[i * 4 + j] = true;
					else
						out[i * 4 + j] = false;
					tempIn /= 2;
				}
			}
		} else {
			int tempIn = 0;

			for (int i = 0; i < in.length(); i++) {
				tempIn = in.charAt(i);
				for (int j = 7; j >= 0; j--) {
					if (tempIn % 2 == 1)
						out[i * 8 + j] = true;
					else
						out[i * 8 + j] = false;
					tempIn /= 2;
				}
			}
		}
	}

	// 灏哹oolean鏁扮粍杞寲涓篶har鏁扮粍,type琛ㄧず16杩涘埗鎴朼sc镰佺殑阃夋嫨
	private void bitToText(char[] out, boolean[] in, boolean type) {
		if (type == true) {
			for (int i = 0; i < 16; i++) {
				int tempIn = 0;
				for (int j = 0; j < 4; j++) {
					if (in[i * 4 + j] == true)
						tempIn = tempIn * 2 + 2;
					else
						tempIn = tempIn * 2;
				}
				tempIn /= 2;
				if (tempIn <= 9 && tempIn >= 0)
					out[i] = (char) (tempIn + 48);
				else
					out[i] = (char) (tempIn + 97 - 10);
			}
		} else {
			for (int i = 0; i < 8; i++) {
				int tempIn = 0;
				for (int j = 0; j < 8; j++) {
					if (in[i * 8 + j] == true)
						tempIn = tempIn * 2 + 2;
					else
						tempIn = tempIn * 2;
				}
				tempIn /= 2;
				out[i] = (char) tempIn;
			}
		}
	}

	// 鍦ㄥ瘑鏂囨樉绀烘鎴栬В瀵嗘樉绀烘鏄剧ず缁撴灉镄勫嚱鏁?
	private void writeToArea(JTextArea area, boolean[] in, boolean type) {
		if (type == true) {
			char[] temp = new char[16];
			bitToText(temp, in, type);
			String s = new String();
			s = Arrays.toString(temp);
			s = s.replace(s.substring(s.length() - 1, s.length()), "");
			s = s.replace(s.substring(0, 1), "");
			s = s.replace(", ", "");

			area.append(s);
		} else {
			char[] temp = new char[8];
			bitToText(temp, in, type);
			String s = new String();
			s = Arrays.toString(temp);
			s = s.replace(s.substring(s.length() - 1, s.length()), "");
			s = s.replace(s.substring(0, 1), "");
			s = s.replace(", ", "");

			area.append(s);
		}
	}

	// DES瀹炵幇锷犺В瀵嗙殑鍑芥暟锛宑hoose涓篓rue锷犲瘑锛宑hoose涓篺alse瑙ｅ瘑
	private void DES(boolean choose) {
		if (choose == true) {
			for (int i = 0; i < 64; i++)
				// 鍒濆缃崲
				result[i] = binary[IP[i] - 1];

			for (int i = 0; i < 56; i++)
				nextKey[i] = key[change1[i] - 1]; // 瀵嗛挜缃崲阃夋嫨1

			boolean[] L = new boolean[32];
			boolean[] R = new boolean[32];
			boolean[] C = new boolean[28];
			boolean[] D = new boolean[28];

			for (int i = 0; i < 32; i++) // 鎻愬彇L鍜孯
			{
				L[i] = result[i];
				R[i] = result[i + 32];
			}

			for (int i = 0; i < 28; i++) // 鎻愬彇C鍜娈
			{
				C[i] = nextKey[i];
				D[i] = nextKey[i + 28];
			}

			for (int i = 0; i < 16; i++) // 16杞凯浠?
			{
				keyIteration(C, D, i);
				textIteration(L, R, subKey, i);
			}

			// 浜掓崲L鍜孯
			for (int i = 0; i < 32; i++) {
				binary[i] = R[i];
				binary[i + 32] = L[i];
			}
			// 阃嗗垵濮嬬疆鎹㈠缑鍒板瘑鏂?
			for (int i = 0; i < 64; i++)
				result[i] = binary[IP1[i] - 1];

			writeToArea(cipherArea, result, type);
		} else {
			for (int i = 0; i < 64; i++)
				// 鍒濆缃崲
				binary[i] = result[IP[i] - 1];

			boolean[] L = new boolean[32];
			boolean[] R = new boolean[32];

			for (int i = 0; i < 32; i++) // 鎻愬彇L鍜孯
			{
				L[i] = binary[i];
				R[i] = binary[i + 32];
			}

			for (int i = 15; i >= 0; i--) // 16杞凯浠?
			{
				textIteration(L, R, subKey, i);
			}

			// 浜掓崲L鍜孯
			for (int i = 0; i < 32; i++) {
				result[i] = R[i];
				result[i + 32] = L[i];
			}
			// 阃嗗垵濮嬬疆鎹㈠缑鍒板瘑鏂?
			for (int i = 0; i < 64; i++)
				binary[i] = result[IP1[i] - 1];

			writeToArea(showArea, binary, type);
		}
	}

	// 瀵嗛挜镄勪竴杞凯浠?
	private void keyIteration(boolean[] C, boolean[] D, int seq) {
		for (int i = 0; i < leftShift[seq]; i++) // 鍚戝乏寰幆绉讳綅
		{
			boolean tempC = C[0];
			boolean tempD = D[0];

			for (int j = 0; j < 27; j++) {
				C[j] = C[j + 1];
				D[j] = D[j + 1];
			}
			C[27] = tempC;
			D[27] = tempD;
		}

		// 缃崲阃夋嫨2锛屼骇鐢熷瓙瀵嗛挜
		for (int i = 0; i < 48; i++) {
			if (change2[i] < 29)
				subKey[seq * 48 + i] = C[change2[i] - 1];
			else
				subKey[seq * 48 + i] = D[change2[i] - 29];
		}
	}

	// 鏂囩珷镄勪竴杞凯浠?
	private void textIteration(boolean[] L, boolean[] R, boolean[] subKey, int seq) {
		boolean[] tempR = new boolean[32];
		for (int i = 0; i < 32; i++)
			// FFunction灏嗘敼鍙楻锛屾澶勪缭瀛楻
			tempR[i] = R[i];

		FFunction(R, subKey, seq);

		for (int i = 0; i < 32; i++)
			// 寮傛垨寰楀埌鏂扮殑R
			R[i] ^= L[i];

		for (int i = 0; i < 32; i++)
			// 寰楀埌鏂扮殑L
			L[i] = tempR[i];
	}

	// F鍑芥暟
	private void FFunction(boolean[] R, boolean[] subKey, int seq) {
		boolean[] extendR = new boolean[48];

		for (int i = 0; i < 48; i++)
			// 镓╁睍R涓?8浣?
			extendR[i] = R[E[i] - 1];

		for (int i = 0; i < 48; i++)
			// XOR
			extendR[i] ^= subKey[seq * 48 + i];

		boolean[] SR = new boolean[32]; // 缁厂鐩掑悗镄凴
		for (int i = 0; i < 8; i++) {
			int[] temp = new int[6];
			for (int j = 0; j < 6; j++) {
				if (extendR[i * 6 + j] == true)
					temp[j] = 1;
				else
					temp[j] = 0;
			}

			int tempSx = 2 * temp[0] + temp[5];
			int tempSy = 8 * temp[1] + 4 * temp[2] + 2 * temp[3] + temp[4];

			SBox(SR, tempSx, tempSy, i);
		}

		boolean[] PR = new boolean[32]; // 缁忕疆鎹鍚庣殑R
		for (int i = 0; i < 32; i++) {
			PR[i] = SR[P[i] - 1];
		}

		for (int i = 0; i < 32; i++)
			// 鏀瑰彉R镄勫�
			R[i] = PR[i];
	}

	// S鐩掓搷浣?
	private void SBox(boolean[] SR, int x, int y, int seq) {
		int SNum = S[seq][x * 16 + y];

		for (int i = 3; i >= 0; i--) {
			if (SNum % 2 == 1)
				SR[seq * 4 + i] = true;
			else
				SR[seq * 4 + 1] = false;
			SNum /= 2;
		}
	}

	// 涓诲嚱鏁?
	public static void main(String[] args) {
		new DESTEST();
	}
}
