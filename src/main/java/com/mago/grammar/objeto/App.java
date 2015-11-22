package com.mago.grammar.objeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
		String str="    ;LOCAL nombre:DWORD\n"
				+ "    ;LOCAL Buffer[128]:BYTE\n"
				+ ".486\n"
				+ ".model flat, stdcall\n"
				+ "option casemap :none\n"
				+ "\n"
				+ "include \\masm32\\include\\windows.inc\n"
				+ "include \\masm32\\macros\\macros.asm\n"
				+ "\n"
				+ "include \\masm32\\include\\masm32.inc\n"
				+ "include \\masm32\\include\\gdi32.inc\n"
				+ "include \\masm32\\include\\user32.inc\n"
				+ "include \\masm32\\include\\kernel32.inc\n"
				+ "\n"
				+ "includelib \\masm32\\lib\\masm32.lib\n"
				+ "includelib \\masm32\\lib\\gdi32.lib\n"
				+ "includelib \\masm32\\lib\\user32.lib\n"
				+ "includelib \\masm32\\lib\\kernel32.lib\n"
				+ "\n"
				+ "show_text PROTO :DWORD\n"
				+ "\n"
				+ ".code\n"
				+ "\n"
				+ "start:\n"
				+ "    call main\n"
				+ "    exit\n"
				+ "\n"
				+ "main proc\n"
				+ "    LOCAL txtinput:DWORD\n"
				+ "    mov txtinput, input(\""+cadena+ "\")\n"
				+ "    invoke show_text, txtinput\n"
				+ "    ret\n"
				+ "main endp\n"
				+ "\n"
				+ "show_text proc string:DWORD\n"
				+ "    print chr$(\" \",13,10 )\n"
				+ "    print string\n"
				+ "    ret\n"
				+ "show_text endp\n"
				+ "\n"
				+ "end start";
		return str;		
	}
	
	public void generatePDF(String str) throws FileNotFoundException, DocumentException{
		FileOutputStream file = new FileOutputStream("obj/obj.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, file);
		document.open();
		document.add(new Paragraph(str));
		document.close();
	}
	
	public void codigoObj(String cadena){
		try {
			crearCodigo(generarTextoAsm(cadena));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exeASM() throws IOException, InterruptedException{
		Process proceso=Runtime.getRuntime().exec("ml /c /Cp /coff obj/obj.asm");// con esta linea generas el .obj
		java.io.InputStream is=proceso.getInputStream();//flujo para crear el .obj
		BufferedReader bf=new BufferedReader(new InputStreamReader(is));

		while((bf.readLine())!=null){}// este bucle sepa su madre por que sea necesario pero si no lo ponia me mandaba error
		Runtime.getRuntime().exec("link /subsystem:console obj.obj");//generas el .exe
		Thread.sleep(500);
		Runtime.getRuntime().exec("cmd /c start obj.exe");//ejecutas el .exe
	}
	
}