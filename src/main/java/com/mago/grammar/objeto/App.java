package com.mago.grammar.objeto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App{
	
	public App(){
		
	}
	
	private void crearCodigo(String cadena) throws IOException{
		String path="obj/obj.asm";
		File file = new File(path);
		BufferedWriter writer;
		if(file.exists()){
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(cadena);
		}else{
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(cadena);
		}
		writer.close();
	}
	
	private String generarTextoAsm(String cadena){
		String str="        .486\n"
				+ "        .model flat, stdcall\n"
				+ "        option casemap  :none\n"
				+ "\n"
				+ "        include \\masm32\\include\\windows.inc\n"
				+ "        include \\masm32\\macros\\macros.asm\n"
				+ "\n"
				+ "        include \\masm32\\include\\masm32.inc\n"
				+ "        include \\masm32\\include\\gdi32.inc\n"
				+ "        include \\masm32\\include\\user32.inc\n"
				+ "        include \\masm32\\include\\kernel32.inc\n"
				+ "\n"
				+ "        includelib \\masm32\\lib\\masm32.lib\n"
				+ "        includelib \\masm32\\lib\\gdi32.lib\n"
				+ "        includelib \\masm32\\lib\\user32.lib\n"
				+ "        includelib \\masm32\\lib\\kernel32.lib\n"
				+ "\n"
				+ "        .code\n"
				+ "\n"
				+ "		start:\n"
				+ "	   		print chr$(\"" +cadena+"\",13,10)\n"
				+ "			exit\n"
				+ "		end start";
		return str;		
	}
	
	public void codigoObj(String cadena){
		try {
			crearCodigo(generarTextoAsm(cadena));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}