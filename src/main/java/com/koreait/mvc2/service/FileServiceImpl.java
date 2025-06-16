package com.koreait.mvc2.service;

import com.koreait.mvc2.dao.FileDAO;
import com.koreait.mvc2.dto.FileDTO;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.util.List;
import java.util.UUID;

public class FileServiceImpl implements FileService {
    private FileDAO fileDAO;
    private ServletContext context;

    public FileServiceImpl(ServletContext context) {
        this.context = context;
        this.fileDAO = new FileDAO();
    }

    @Override
    public FileDTO upload(int boardIdx, Part part) throws Exception {
        String original = extractFilename(part);
        if (original == null) return null;
        String uuid = UUID.randomUUID().toString();
        String stored = uuid + "_" + original;
        String realPath = context.getRealPath("/uploads");
        File dir = new File(realPath);
        if (!dir.exists()) dir.mkdirs();
        part.write(realPath + File.separator + stored);

        FileDTO f = new FileDTO();
        f.setBoardIdx(boardIdx);
        f.setOriginalName(original);
        f.setStoredName(stored);
        f.setContentType(part.getContentType());
        f.setSize(part.getSize());
        f.setUploadPath("/uploads");
        fileDAO.insertFile(f);
        return f;
    }

    @Override
    public FileDTO getFile(int fileIdx) throws Exception {
        return fileDAO.selectFile(fileIdx);
    }

    @Override
    public List<FileDTO> getFilesByBoard(int boardIdx) throws Exception {
        return fileDAO.selectFilesByBoardIdx(boardIdx);
    }

    private String extractFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fn = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fn.isEmpty() ? null : fn;
            }
        }
        return null;
    }
}