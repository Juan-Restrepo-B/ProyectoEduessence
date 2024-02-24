package eduessence.registro_cliente.models.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
    private String code;

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public static class FormatoEmailIncorrectoException extends RuntimeException {
        public FormatoEmailIncorrectoException(String mensaje) {
            super(mensaje);
        }
    }
    public static class LongitudCampoException extends RuntimeException {
        public LongitudCampoException(String mensaje) {
            super(mensaje);
        }
    }

}
