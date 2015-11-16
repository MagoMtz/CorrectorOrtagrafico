package com.mago.grammar.spelling;

import java.io.IOException;
import java.util.List;

import org.languagetool.tools.Tools;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;

public class Ortografia {
	
	public Ortografia(){
		
	}
	
	public String checkOrtografia(String cadena){
		System.out.println("Tu cadena: " +cadena);
    	JLanguageTool langTool = new JLanguageTool(new Spanish());
    	String mensaje="";
    	String error="";
    	try{
    		List<RuleMatch> matches = langTool.check(cadena);
    		for (RuleMatch match : matches){
    	        error =("Error en la línea " +match.getLine() + ", espacio " +match.getColumn() + ": " + match.getMessage()+"\n"+"Palabras sugeridas: " +
    	    	        match.getSuggestedReplacements());
    	        mensaje=mensaje+"\n"+error;
    		}
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	System.out.println(mensaje);
    	return mensaje;
	}
	
	public String editOrtografia(String cadena, JLanguageTool lt){
		String correccion="";
		try {
			correccion = Tools.correctText(cadena, lt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Corregida: "+correccion);
		return correccion;
	}

}
