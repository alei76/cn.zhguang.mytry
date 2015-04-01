package com.taobao.yinggang.nomal.mp3Recode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Mp3TagRecode {
	public Mp3TagRecode() throws FileNotFoundException {
	}

	public static void main(String[] args) throws IOException {
		Mp3TagRecode read = new Mp3TagRecode();
		byte[] buffer = new byte[128];
		RandomAccessFile ran = null;
		File file = null;
		file = new File("E:\\new\\走进新时�?mp3");
		ran = new RandomAccessFile(file, "r");
		ran.seek(ran.length() - 128);

		ran.read(buffer);
		Mp3Info info = new Mp3Info(buffer);
		System.out.println("name:" + file.getName() + " title:" + info.getSongName() + " year:" + info.getYear()
				+ " 歌�׹:" + info.getArtist() + " 专辑:" + info.getAlbum() + " 备注:" + info.getComment());

	}
}