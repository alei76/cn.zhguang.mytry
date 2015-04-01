package com.taobao.yinggang.nomal.mp3Recode;


public class Mp3Info {

	private final String TAG = "TAG"; // 文件�?-3

	private String songName; // 歌曲�?-33

	private String artist; // 歌�׹�?4-63

	private String album; // 专辑�?1-93

	private String year; // �?4-97

	private String comment; // 备注98-125

	private byte r1, r2, r3; // 三个保留�?26�?27�?28

	private boolean valid; // 是否合法

	public transient String fileName; // 此歌��对��욄文件�?没有封装

	public Mp3Info(byte[] data) {
		if (data.length != 128) {
			throw new RuntimeException("数据长度不合�?" + data.length);
		}
		String tag = new String(data, 0, 3);
		// 只有�ո��个字节是TAG�؍处理后�Ǣ的字节

		if (tag.equalsIgnoreCase("TAG")) {
			try {
				String charSet = "UTF-16";
				valid = true;
				songName = new String(data, 3, 30, charSet).trim();
				artist = new String(data, 33, 30, charSet).trim();
				album = new String(data, 63, 30, charSet).trim();
				year = new String(data, 93, 4, charSet).trim();
				comment = new String(data, 97, 28, charSet).trim();

				r1 = data[125];
				r2 = data[126];
				r3 = data[127];
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
				valid = false;
			}
		} else {
			valid = false;
		}
	}

	public Mp3Info() {
	}

	/**
	 * 返回是否合法
	 * 
	 * @return 是否
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * 得到此对象的128个字节的表示形��
	 * 
	 * @return
	 */
	public byte[] getBytes() {
		byte[] data = new byte[128];
		System.arraycopy(TAG.getBytes(), 0, data, 0, 3);
		byte[] temp = songName.getBytes();
		System.arraycopy(temp, 0, data, 3, temp.length > 30 ? 30 : temp.length);
		temp = artist.getBytes();
		System.arraycopy(temp, 0, data, 33, temp.length > 30 ? 30 : temp.length);
		temp = album.getBytes();
		System.arraycopy(temp, 0, data, 63, temp.length > 30 ? 30 : temp.length);
		temp = year.getBytes();
		System.arraycopy(temp, 0, data, 93, temp.length > 4 ? 4 : temp.length);
		temp = comment.getBytes();
		System.arraycopy(temp, 0, data, 97, temp.length > 28 ? 28 : temp.length);
		data[125] = r1;
		data[126] = r2;
		data[127] = r3;
		return data;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String authorName) {
		this.artist = authorName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte getR1() {
		return r1;
	}

	public void setR1(byte r1) {
		this.r1 = r1;
	}

	public byte getR2() {
		return r2;
	}

	public void setR2(byte r2) {
		this.r2 = r2;
	}

	public byte getR3() {
		return r3;
	}

	public void setR3(byte r3) {
		this.r3 = r3;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		if (songName == null) {
			throw new NullPointerException("歌名不能是null!");
		}
		valid = true;
		this.songName = songName;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String specialName) {
		this.album = specialName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}