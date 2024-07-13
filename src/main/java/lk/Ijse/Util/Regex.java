package lk.Ijse.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextFieldRegex textField,String text) {
        String filed="";
        switch (textField) {
            case ID:
                filed="^([A-Z][0-9]{3})$";
                break;
            case NAME:
                filed="^[A-z|\\\\s]{3,}$";
                break;
            case PRICE:
                filed= "^\\d([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case ADDRESS:
                filed="[A-za-z\\\\s]{1,}";
                break;
            case CONTACT:
                filed="^([+]94{1,3}|[0])([1-9]{2})([0-9]){1,7}$";
                break;
            case SALARY:
                filed= "^\\d([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case EMAIL:
                filed="^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case QTY:
                filed="^\\d+$";
                break;
        }
        Pattern pattern = Pattern.compile(filed);
        if (text!=null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean setTextColor(TextFieldRegex location, TextField textField) {
        if (Regex.isTextFieldValid(location,textField.getText())){
            textField.setStyle("-fx-border-color: rgba(0,255,0,255);");
            textField.setStyle("-fx-border-color: rgba(0,255,0,255);");
            return true;
        }else {
            textField.setStyle("-fx-border-color: rgba(255,0,0,255);");
            textField.setStyle("-fx-border-color: rgba(255,0,0,255);");
            return false;
        }
    }

}

