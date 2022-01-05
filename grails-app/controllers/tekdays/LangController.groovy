package tekdays

import grails.converters.JSON
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver

class LangController {

    def changeLang() {
        def lang = params.lang
        session.setAttribute('lang', lang)
        def url = request.getHeader("referer").split("\\?")[0]

        switch (lang) {
            case "en":
                url = url + "?lang=${lang}"
                break
            case "ru":
                url = url + "?lang=${lang}"
                break
            case "hy":
                url = url + "?lang=${lang}"
                break
            default:
                url = url + "?lang=en"
                break

        }
        render(url)

    }


    def checkLang() {
        def lang = session?.lang
        if (lang != null) {

            render(lang)
        } else render('en')

    }
}
