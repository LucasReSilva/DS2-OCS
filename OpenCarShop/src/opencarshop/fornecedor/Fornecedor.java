package opencarshop.fornecedor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fornecedor {

	private String CNPJ;

	private String RazaoSocial;

	private String Descricao;

        private String Email;
        
	private String Telefone1;

	private String Telefone2;
        
        private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        private static boolean validarCNPJ(String cnpj){
            
            
            return false;           
        }
        
        private static boolean validarEmail(String email){
            
            final String PADRAO_EMAIL = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            
            final Pattern PADRAO = Pattern.compile(PADRAO_EMAIL, Pattern.CASE_INSENSITIVE);
            
            Matcher casador;
            casador = PADRAO.matcher(email);
            
            return casador.matches(); 
        }
}
