package tekdays

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver

class LangController {

    def changeLang() {
        def lang = params.lang
        def url = request.getHeader("referer") + "?lang=en"

        switch (lang) {
            case "en":
                url = request.getHeader("referer") + "?lang=${lang}"
                break
            case "ru":
                url = request.getHeader("referer") + "?lang=${lang}"
                break
            case "hy":
                url = request.getHeader("referer") + "?lang=${lang}"
                break
            default:
                url = request.getHeader("referer") + "?lang=en"
                break

        }


        render(url)

    }
}
