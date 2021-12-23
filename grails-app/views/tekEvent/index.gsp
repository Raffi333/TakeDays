<%@ page import="tekdays.TekEvent" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>




    <script>

        $(document).ready(function () {
            $('#dt').DataTable({
                sScrollY: "75%",
                sScrollX: "100%",
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: "/TekDays/tekEvent/dataTablesRenderer",
                bJQueryUI: false,
                bAutoWidth: false,
                sPaginationType: "full_numbers",
                aLengthMenu: [5, 10, 25, 50, 100, 200],
                iDisplayLength: 10,
                aoColumnDefs: [
                    {
                        // bSearchable: false,
                        render: function (data, type, full, meta) {
                            if (full) {
                                return '<a href="${createLink(controller: 'TekEvent', action: 'show')}/' + full[0] + '"class="btn">' + data + '</a>';
                            } else {
                                return data;
                            }
                        },
                        aTargets: [0]
                    },
                    {
                        createdCell: function (td, cellData, rowData, row, col) {
                            // console.log(td)
                            // console.log(cellData)
                            // console.log(rowData)
                            // console.log(row)
                            // console.log(col)
                            $(td).attr('style', 'color: red;');
                        },
                        // bSearchable: false,
                        bSortable: false,
                        visible: true,
                        aTargets: [1, 2,3]

                    }

                ]
            });
        });
    </script>
</head>

<body>
<a href="#list-tekEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-tekEvent" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>






    <table class="table-bordered" id="dt">
        <thead>
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>city</th>
            <th>organizer</th>
            <th>venue</th>
            <th>startDate</th>
            <th>endDate</th>
        </tr>
        </thead>
    </table>


    <div class="pagination">
        <g:paginate total="${tekEventInstanceCount ?: 0}"/>
    </div>
</div>

</body>
</html>