package eduessence.iniciar_sesion.models.SendMail;

public class MessageHTML {
    public static final String RECUPERAR_CLAVE = "<!DOCTYPE html>\n" +
            "<html lang=\"es\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Correo de Eduessence Academy</title>\n" +
            "    <link rel=\"stylesheet\" href=\"styles.css\">\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
            "        style=\"max-width: 600px; margin: auto; background-color: #ffffff; border: 1px solid #ddd;\">\n" +
            "        <tr>\n" +
            "            <td style=\"text-align: center; padding: 20px;\">\n" +
            "                <img src=\"https://www.eduessence.com/img/logo.png\" alt=\"Logo de Eduessence\" width=\"300\">\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"text-align: right; padding-right: 20px; font-size: 12px;\">\n" +
            "                {{fecha}}\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"padding: 20px; text-align: left;\">\n" +
            "                <p style=\"font-size: 16px; font-weight: bold;\">Buen día, Sr(a). {{nombreApellido}}</p>\n" +
            "                <p>Para el comité de Eduessence, es un placer contar con ustedes dentro del grupo de Eduessence\n" +
            "                    Academy en esta gran academia de impacto latinoamericano, desarrollado webinars, charlas,\n" +
            "                    talleres, congresos, simposios, cursos a la medida, entre otros.</p>\n" +
            "                <p>Nos complace compartir de manera virtual y/o presencial con todos en nuestros eventos.</p>\n" +
            "                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
            "                    style=\"background-color: #eef; padding: 10px; border-left: 5px solid #007bff;\">\n" +
            "                    <tr>\n" +
            "                        <td>\n" +
            "                            <p style=\"font-size: 16px; font-weight: bold; margin: 0;\">CODIGO DE VERIFICACIÓN</p>\n" +
            "                            <p>Usuario: ((username))</p>\n" +
            "                            <p>Código: {0}{1}{2}{3}{4}{5}</p>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                </table>\n" +
            "                <p>Esperamos contar con ustedes en nuestros próximos eventos.</p>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td style=\"text-align: left; padding: 20px;\">\n" +
            "                Cordial saludo,<br>\n" +
            "                <img src=\"https://www.eduessence.com/img/logo.png\" alt=\"Firma Correos\" width=\"450\">\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
