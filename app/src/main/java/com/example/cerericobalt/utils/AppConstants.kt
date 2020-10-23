package com.example.cerericobalt.utils

object AppConstants {
    const val CERERE_CONCEDIU = "cere_concediu"
    const val CERERE_INVOIRE = "cerere_invoire"
    const val REQUEST_TYPE = "request_type"
    const val PAID_LEAVE = "paid_leave"
    const val LEAVE_REQUEST = "leave_request"

    const val EMPLOYEE_NAME_KEY = "{EMPLOYEE_NAME}"
    const val EMPLOYEE_OCCUPIED_POSITION = "{EMPLOYEE_POSITION}"
    const val START_DATE_KEY = "{START_DATE}"
    const val END_DATE_KEY = "{START_DATE}"
    const val NR_OF_DAYS_KEY = "{NR_OF_DAYS}"
    const val LEAVE_DATE_KEY = "{LEAVE_DATE}"
    const val START_TIME_KEY = "{START_TIME}"
    const val END_TIME_KEY = "{END_TIME}"
    const val REQUEST_REASON_KEY = "{REQUEST_REASON}"
    const val RECUPERATION_PERIOD_KEY = "{RECUPERATION_PERIOD}"
    const val FILL_DATE_KEY = "{SIGNED_DATE}"


    const val CONCEDIU_HTML = "<html lang=\"ro\">\n" +
            "\n" +
            "<head>\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "  <style>\n" +
            "    html {\n" +
            "      -ms-text-size-adjust: 100%;\n" +
            "      -webkit-text-size-adjust: 100%;\n" +
            "      font-family: \"centered-div\", sans-serif;\n" +
            "    }\n" +
            "\n" +
            "    body {\n" +
            "      margin: 2em 5em 2em 5em;\n" +
            "    }\n" +
            "\n" +
            "    .title-text {\n" +
            "      align-items: center;\n" +
            "      font-size: 28px;\n" +
            "      font-weight: 350;\n" +
            "    }\n" +
            "\n" +
            "    .centered-div {\n" +
            "      display: flex;\n" +
            "      justify-content: center;\n" +
            "    }\n" +
            "\n" +
            "    .justify-div {}\n" +
            "\n" +
            "    .div-request-text {\n" +
            "      line-height: 50px;\n" +
            "      text-align: justify;\n" +
            "    }\n" +
            "\n" +
            "    .div-date-signature {\n" +
            "      display: flex;\n" +
            "      flex-direction: row;\n" +
            "      justify-content: space-between;\n" +
            "    }\n" +
            "\n" +
            "    .div-date {\n" +
            "      display: flex;\n" +
            "      flex-direction: column;\n" +
            "      line-height: 30px;\n" +
            "    }\n" +
            "\n" +
            "    .nr-inmatriculare {\n" +
            "      margin-left: auto;\n" +
            "      text-align: right;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "  <div class=\"centered-div\">\n" +
            "    <img style=\"justify-content: center;\"\n" +
            "      src=\"https://spotlight-timisoara.eu/wp-content/uploads/2019/11/logo-cobalt-sign-300x300.jpeg\" width=\"100\"\n" +
            "      height=\"100\">\n" +
            "  </div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"centered-div\">\n" +
            "    <span class=\"title-text\">CERERE CONCEDIU DE ODIHNĂ</span>\n" +
            "  </div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"nr-inmatriculare \">Nr.____ /__________</div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div>Către conducerea COBALT SIGN SRL</div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"div-request-text\">\n" +
            "    Subsemnatul/a, <b>{EMPLOYEE_NAME}</b>\n" +
            "    angajat al Cobat Sign SRL, având funcția de\n" +
            "    <b>{EMPLOYEE_POSITION}</b>\n" +
            "    prin prezenta vă rog să-mi aprobațiefectuarea concediului de odihnă în\n" +
            "    urmă toarele date/intervale <b>{START_DATE}</b> - <b>{END_DATE}</b>\n" +
            "    însumând <b>{NR_OF_DAYS}</b> zile lucrătoare.\n" +
            "  </div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <div class=\"div-request-text\">Vă mulțumesc anticipat.</div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"div-date-signature\">\n" +
            "    <div class=\"div-date\">\n" +
            "      <div>DATA</div>\n" +
            "      <div>{SIGNED_DATE}</div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"div-date\">\n" +
            "      <div>Semnatura</div>\n" +
            "      <div>{SIGNATURE_IMAGE}</div>\n" +
            "    </div>\n" +
            "\n" +
            "  </div>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>"

    const val INVOIRE_HTML = "<html lang=\"ro\">\n" +
            "\n" +
            "<head>\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "  <style>\n" +
            "    html {\n" +
            "      -ms-text-size-adjust: 100%;\n" +
            "      -webkit-text-size-adjust: 100%;\n" +
            "      font-family: \"centered-div\", sans-serif;\n" +
            "    }\n" +
            "\n" +
            "    body {\n" +
            "      margin: 2em 5em 2em 5em;\n" +
            "    }\n" +
            "\n" +
            "    .title-text {\n" +
            "      align-items: center;\n" +
            "      font-size: 28px;\n" +
            "      font-weight: 350;\n" +
            "    }\n" +
            "\n" +
            "    .centered-div {\n" +
            "      display: flex;\n" +
            "      justify-content: center;\n" +
            "    }\n" +
            "\n" +
            "    .justify-div {}\n" +
            "\n" +
            "    .div-request-text {\n" +
            "      line-height: 50px;\n" +
            "      text-align: justify;\n" +
            "    }\n" +
            "\n" +
            "    .div-date-signature {\n" +
            "      display: flex;\n" +
            "      flex-direction: row;\n" +
            "      justify-content: space-between;\n" +
            "    }\n" +
            "\n" +
            "    .div-date {\n" +
            "      display: flex;\n" +
            "      flex-direction: column;\n" +
            "      line-height: 30px;\n" +
            "    }\n" +
            "\n" +
            "    .nr-inmatriculare {\n" +
            "      margin-left: auto;\n" +
            "      text-align: right;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "  <div class=\"centered-div\">\n" +
            "    <img style=\"justify-content: center;\"\n" +
            "      src=\"https://spotlight-timisoara.eu/wp-content/uploads/2019/11/logo-cobalt-sign-300x300.jpeg\" width=\"100\"\n" +
            "      height=\"100\">\n" +
            "  </div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"centered-div\">\n" +
            "    <span class=\"title-text\">CERERE CONCEDIU DE ODIHNĂ</span>\n" +
            "  </div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"nr-inmatriculare \">Nr.____ /__________</div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div>Către conducerea COBALT SIGN SRL</div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"div-request-text\">\n" +
            "    Subsemnatul/a, <b>{EMPLOYEE_NAME}</b> \n" +
            "    angajat al Cobat Sign SRL, având funcția de \n" +
            "    <b>{EMPLOYEE_POSITION}</b> \n" +
            "    prin prezenta prezenta solicit învoire în data de  <b>{LEAVE_DATE}</b> între \n" +
            "    <b>{START_TIME}</b> -  <b>{END_TIME}</b>. Motivul solicitării: \n" +
            "    <b>{REQUEST_REASON}</b>.\n" +
            "    <br/>\n" +
            "    Urmează să recuperez aceste ore, în cadrul aceleiași luni, după programul: \n" +
            "    <b>{RECUPERATION_PERIOD}</b>.\n" +
            "  </div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <div class=\"div-request-text\">Vă mulțumesc anticipat.</div>\n" +
            "\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "  <br />\n" +
            "\n" +
            "  <div class=\"div-date-signature\">\n" +
            "    <div class=\"div-date\">\n" +
            "      <div>DATA</div>\n" +
            "      <div>{SIGNED_DATE}</div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"div-date\">\n" +
            "      <div>Semnatura</div>\n" +
            "      <div>{SIGNATURE_IMAGE}</div>\n" +
            "    </div>\n" +
            "\n" +
            "  </div>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>"
}