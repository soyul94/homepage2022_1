package egovframework.let.utl.fcc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Component("fileMngUtil")
public class FileMngUtil {
	public static final int BUFF_SIZE= 2048;
	
	@Resource(name= "propertiesService")
	protected EgovPropertyService propertyService;
	
	@Resource(name= "egovFileIdGnrService")
	private EgovIdGnrService idgenService;
	
	
	//반환형으로 사용된 List<>는 java.util.List
	//첨부파일에 대한 목록 정보를 취득한다.
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, 
									 String KeyStr, 
									 int fileKeyParam, //디폴트 값은 0
									 String atchFileId, //첨부파일 아이디
									 String storePath //저장 경로
									 ) throws Exception{
		int fileKey= fileKeyParam; //첨부파일로 받은 파일리스트의 인덱스
		
		//파일 저장경로
		String storePathString="";
		//첨부파일 ID
		String atchFileIdString= "";
		
		//파일 저장경로 여부
		if("".equals(storePath) || storePath==null) {
			storePathString= propertyService.getString("Globals.filestorePath"); //디폴트 경로
		}
		else {
			storePathString= propertyService.getString(storePath); //요청받은 경로		
		}
		
		//첨부파일ID 생성 및 업데이트 여부
		if("".equals(atchFileId) || atchFileId==null) {
			atchFileIdString= idgenService.getNextStringId(); //첨부파일 아이디를 새로 생성 
		}
		else {
			atchFileIdString= atchFileId; //기존의 아이디를 사용
		}
		
		//폴더경로 설정		 //File: java.io
		File saveFolder= new File(storePathString); 
		if(!saveFolder.exists() || saveFolder.isFile()) { //지정한 경로가 존재하지 않는다면 경로에 맞게 폴더를 생성함
			saveFolder.mkdir();
		}
		
		//파일변수    //Iterator, Entry : java.util
		Iterator<Entry<String, MultipartFile>> itr= files.entrySet().iterator();
		MultipartFile file;
		String filePath= "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;
		
		while(itr.hasNext()) {
			Entry<String, MultipartFile> entry= itr.next();
			
			file= entry.getValue();
			String orginFileName= file.getOriginalFilename(); //원본 파일명
			
			//-----------------------------------
			// 원본 파일명이 없는 경우(첨부파일이 존재하지 않는 것) 처리 
			// (첨부가 되지 않은 input file type)
			//-----------------------------------
			if("".equals(orginFileName)) {
				continue;
			}
			//-----------------------------------
			
			//파일확장자 체크
			int index = orginFileName.lastIndexOf("."); //이름의 마지막 .을 검색하여 확장자를 찾음
			String fileExt = orginFileName.substring(index+1); //파일의 확장자를 가져옴
			
			//저장파일명				//EgovStringUtil: egovframework.let.utl.fcc.service.EgovStringUtil
			String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey;
			//원본파일명을 그대로 DB에 저장하면 기존 데이터와의 중복이 문제될 수 있기 때문에 원본파일명은 따로 보관하되 이름을 변경해서 저장한다.
			
			//파일사이즈
			long size = file.getSize();
			
			//파일저장: 
			if(!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				//		  	 저장위치				폴더 구분자 		     새로운 이름
				file.transferTo(new File(filePath));// 저장함
			}
			fvo = new FileVO();
			fvo.setFileExtsn(fileExt);
			fvo.setFileStreCours(storePathString);
			fvo.setFileMg(Long.toString(size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));
			
			result.add(fvo);
			
			fileKey++;
		}
		
		return result;
	}
}
