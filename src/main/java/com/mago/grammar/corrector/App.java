package com.mago.grammar.corrector;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;

import com.mago.grammar.spelling.Ortografia;


public class App{
	private static final String CADENA="Escriba un texto aquí. LanguageTool le ayudará a afrentar algunas dificultades propias de la escritura. Se a hecho un esfuerzo para detectar errores tipográficos, ortograficos y incluso gramaticales. También algunos errores de estilo, a grosso modo.";
	
    public static void main(String[] args){
        Ortografia spell = new Ortografia();
        spell.checkOrtografia(CADENA);
        spell.editOrtografia(CADENA, new JLanguageTool(new Spanish()));
    	
   }
}