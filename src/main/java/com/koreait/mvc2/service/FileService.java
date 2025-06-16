package com.koreait.mvc2.service;

import com.koreait.mvc2.dto.FileDTO;
import javax.servlet.http.Part;
import java.util.List;

public interface FileService {
    FileDTO upload(int boardIdx, Part part) throws Exception;
    FileDTO getFile(int fileIdx) throws Exception;
    List<FileDTO> getFilesByBoard(int boardIdx) throws Exception;
}