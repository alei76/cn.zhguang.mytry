package com.taobao.yinggang.nomal.myJava;

public class FaceData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] m_iX = new int[76], m_iY = new int[76], m_iZ = new int[76];
		int[][] draw = new int[76][3];

		m_iX[0] = 0;
		m_iY[0] = 250;
		m_iZ[0] = 0;
		m_iX[1] = 40;
		m_iY[1] = 190;
		m_iZ[1] = 0;
		m_iX[2] = 0;
		m_iY[2] = 130;
		m_iZ[2] = 0;
		m_iX[3] = 0;
		m_iY[3] = 70;
		m_iZ[3] = 0;
		m_iX[4] = 0;
		m_iY[4] = 40;
		m_iZ[4] = 0;
		m_iX[5] = 0;
		m_iY[5] = -50;
		m_iZ[5] = 0;
		m_iX[6] = 0;
		m_iY[6] = -70;
		m_iZ[6] = 30;
		m_iX[7] = 0;
		m_iY[7] = -100;
		m_iZ[7] = 37;
		m_iX[8] = 0;
		m_iY[8] = -120;
		m_iZ[8] = 38;
		m_iX[9] = 0;
		m_iY[9] = -160;
		m_iZ[9] = 0;
		m_iX[10] = 0;
		m_iY[10] = -210;
		m_iZ[10] = 140;
		m_iX[11] = 50;
		m_iY[11] = 245;
		m_iZ[11] = 10;
		m_iX[12] = 105;
		m_iY[12] = 215;
		m_iZ[12] = 20;
		m_iX[13] = 100;
		m_iY[13] = 150;
		m_iZ[13] = 70;
		m_iX[14] = 146;
		m_iY[14] = 130;
		m_iZ[14] = 20;
		m_iX[15] = 120;
		m_iY[15] = 70;
		m_iZ[15] = 70;
		m_iX[16] = 90;
		m_iY[16] = 92;
		m_iZ[16] = 110;
		m_iX[17] = 30;
		m_iY[17] = 70;
		m_iZ[17] = 120;
		m_iX[18] = 90;
		m_iY[18] = 80;
		m_iZ[18] = 0;
		m_iX[19] = 70;
		m_iY[19] = 60;
		m_iZ[19] = 0;
		m_iX[20] = 108;
		m_iY[20] = 40;
		m_iZ[20] = 0;
		m_iX[21] = 70;
		m_iY[21] = 53;
		m_iZ[21] = 4;
		m_iX[22] = 70;
		m_iY[22] = 34;
		m_iZ[22] = 3;
		m_iX[23] = 40;
		m_iY[23] = 40;
		m_iZ[23] = 0;
		m_iX[24] = 70;
		m_iY[24] = 30;
		m_iZ[24] = 10;
		m_iX[25] = 30;
		m_iY[25] = -40;
		m_iZ[25] = 10;
		m_iX[26] = 50;
		m_iY[26] = -60;
		m_iZ[26] = 14;
		m_iX[27] = 120;
		m_iY[27] = -40;
		m_iZ[27] = 50;
		m_iX[28] = 150;
		m_iY[28] = -30;
		m_iZ[28] = 20;
		m_iX[29] = 140;
		m_iY[29] = 40;
		m_iZ[29] = 0;
		m_iX[30] = 110;
		m_iY[30] = -190;
		m_iZ[30] = 60;
		m_iX[31] = 70;
		m_iY[31] = -110;
		m_iZ[31] = 80;
		m_iX[32] = 40;
		m_iY[32] = -180;
		m_iZ[32] = 100;
		m_iX[33] = 10;
		m_iY[33] = -90;
		m_iZ[33] = 136;
		m_iX[34] = -40;
		m_iY[34] = 190;
		m_iZ[34] = 90;
		m_iX[35] = 0;
		m_iY[35] = 130;
		m_iZ[35] = 120;
		m_iX[36] = 0;
		m_iY[36] = 70;
		m_iZ[36] = 130;
		m_iX[37] = 0;
		m_iY[37] = 40;
		m_iZ[37] = 120;
		m_iX[38] = 0;
		m_iY[38] = -50;
		m_iZ[38] = 180;
		m_iX[39] = 0;
		m_iY[39] = -70;
		m_iZ[39] = 130;
		m_iX[40] = 0;
		m_iY[40] = -100;
		m_iZ[40] = 137;
		m_iX[41] = 0;
		m_iY[41] = -120;
		m_iZ[41] = 138;
		m_iX[42] = 0;
		m_iY[42] = -160;
		m_iZ[42] = 120;
		m_iX[43] = 0;
		m_iY[43] = -210;
		m_iZ[43] = 140;
		m_iX[44] = -50;
		m_iY[44] = 245;
		m_iZ[44] = 10;
		m_iX[45] = -105;
		m_iY[45] = 215;
		m_iZ[45] = 20;
		m_iX[46] = -100;
		m_iY[46] = 150;
		m_iZ[46] = 70;
		m_iX[47] = -146;
		m_iY[47] = 130;
		m_iZ[47] = 20;
		m_iX[48] = -120;
		m_iY[48] = 70;
		m_iZ[48] = 70;
		m_iX[49] = -90;
		m_iY[49] = 92;
		m_iZ[49] = 110;
		m_iX[50] = -30;
		m_iY[50] = 70;
		m_iZ[50] = 120;
		m_iX[51] = -90;
		m_iY[51] = 80;
		m_iZ[51] = 110;
		m_iX[52] = -70;
		m_iY[52] = 60;
		m_iZ[52] = 100;
		m_iX[53] = -108;
		m_iY[53] = 40;
		m_iZ[53] = 80;
		m_iX[54] = -70;
		m_iY[54] = 53;
		m_iZ[54] = 104;
		m_iX[55] = -70;
		m_iY[55] = 34;
		m_iZ[55] = 103;
		m_iX[56] = -40;
		m_iY[56] = 40;
		m_iZ[56] = 100;
		m_iX[57] = -70;
		m_iY[57] = 30;
		m_iZ[57] = 100;
		m_iX[58] = -30;
		m_iY[58] = -40;
		m_iZ[58] = 110;
		m_iX[59] = -50;
		m_iY[59] = -60;
		m_iZ[59] = 104;
		m_iX[60] = -120;
		m_iY[60] = -40;
		m_iZ[60] = 50;
		m_iX[61] = -150;
		m_iY[61] = -30;
		m_iZ[61] = 20;
		m_iX[62] = -140;
		m_iY[62] = 40;
		m_iZ[62] = 20;
		m_iX[63] = -110;
		m_iY[63] = -190;
		m_iZ[63] = 60;
		m_iX[64] = -70;
		m_iY[64] = -110;
		m_iZ[64] = 80;
		m_iX[65] = -40;
		m_iY[65] = -180;
		m_iZ[65] = 100;
		m_iX[66] = -10;
		m_iY[66] = -90;
		m_iZ[66] = 136;
		m_iX[67] = 80;
		m_iY[67] = 50;
		m_iZ[67] = 95;
		m_iX[68] = 80;
		m_iY[68] = 35;
		m_iZ[68] = 95;
		m_iX[69] = -80;
		m_iY[69] = 50;
		m_iZ[69] = 95;
		m_iX[70] = -80;
		m_iY[70] = 35;
		m_iZ[70] = 95;
		m_iX[71] = 61;
		m_iY[71] = 50;
		m_iZ[71] = 102;
		m_iX[72] = 61;
		m_iY[72] = 35;
		m_iZ[72] = 102;
		m_iX[73] = -61;
		m_iY[73] = 50;
		m_iZ[73] = 102;
		m_iX[74] = -61;
		m_iY[74] = 35;
		m_iZ[74] = 102;
		m_iX[75] = 0;
		m_iY[75] = -100;
		m_iZ[75] = 50;

		int j;

		for (j = 0; j < 76; j++) {
			draw[j][0] = m_iX[j];
			draw[j][1] = m_iY[j];
			draw[j][2] = m_iZ[j];
			System.out.println(j + " " + draw[j][0] + " " + draw[j][1] + " " + draw[j][2]);
		}

	}

}
