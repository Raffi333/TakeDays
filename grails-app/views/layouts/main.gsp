<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <g:if test="${request.getRequestURI() != "/TekDays/"}">
        <asset:stylesheet src="application.css"/>
    </g:if>
    <asset:javascript src="application.js"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


    <g:layoutHead/>
</head>

<body class="">

<div class="container mb-5 mt-1" style="border: 0.2px solid dodgerblue">

    <div id="logo" role="banner"><a href="${createLink(uri: '/')}">
        <img style="width: 100%;height: 400px;background-size:cover " src="${resource(dir: 'images', file: 'log.png')}"
             alt="TekDays"/></a>

        <g:select name="lang" class="lang" keys="['en', 'ru', 'hy']" from="['English', 'Русские', 'Հայերեն']"/>


        <script>
            $('.lang').click(function () {

                let val = $('.lang').val()
                console.log(val)

                jQuery.ajax({
                    type: 'POST',
                    url: '/TekDays/lang/changeLang',
                    data: {
                        "lang": val,

                    },
                    success: function (data) {
                        window.location = data;
                    }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("ops")
                    }
                })

            })
        </script>


        <div class="container">
            <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                <a href="${createLink(uri: '/')}"
                   class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
                    <g:if test="${session.user}"><span
                            style="font-size: 25px">${'Welcome ' + session.user + "   to - "}</span></g:if>
                    <span class="fs-4">TEK DAYS</span>
                </a>
                <br>

                <ul class="nav nav-pills">
                    <li class="nav-item"><g:loginToggle class="nav-link"/></li>
                    <li class="nav-item"><g:register class="nav-link"/></li>

                </ul>
            </header>
        </div>

    </div>
    <g:layoutBody/>
    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt"
                                                                       default="Loading&hellip;"/></div>

</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>
