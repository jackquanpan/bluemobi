package cn.bluemobi.entity.system;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileEntity {
	
	private List<MultipartFile> files;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}
