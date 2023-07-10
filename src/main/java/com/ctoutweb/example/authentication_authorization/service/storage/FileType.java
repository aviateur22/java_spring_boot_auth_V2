package com.ctoutweb.example.authentication_authorization.service.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public enum FileType {	
	
	JPEG(0xFF, 0xD8, 0xFF), 
	PDF(0x25, 0x50, 0x44),
	PNG(0x89,0x50,0x4E);
	
	private final int[] magicBytes;	
    
    private FileType(int...bytes) {
        magicBytes = bytes;
    }
    
    // Checks if bytes match a specific magic bytes sequence
    public boolean is(byte[] bytes) {
    	
        if (bytes.length != magicBytes.length) {
        	System.out.println(magicBytes.length);
            throw new RuntimeException("I need the first "+magicBytes.length
                    + " bytes of an input stream.");
        }
        	
        for (int i=0; i<bytes.length; i++)
            if (Byte.toUnsignedInt(bytes[i]) != magicBytes[i])
                return false;
        return true;
    }
    
    // Extracts head bytes from any stream
    public static byte[] extract(InputStream is, int length) throws IOException {
        try (is) {  // automatically close stream on return
            byte[] buffer = new byte[length];
            is.read(buffer, 0, length);
            return buffer;
        }
    }
    
    /* Convenience methods */
    public boolean is(File file) throws IOException {
        return is(new FileInputStream(file));
    }
    
    public boolean is(InputStream is) throws IOException {
        return is(extract(is, magicBytes.length));
    }
}
