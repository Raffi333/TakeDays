<h3>Sponsors</h3>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Web Site</th>
        <th>Contribution</th>
    </tr>
    </thead>
    <g:each in="${sponsorships}" var="s">
        <tr>
            <td>
                <g:link controller="sponsor" action="show" id="${s.sponsor.id}">
                    ${s.sponsor.name}
                </g:link>
            </td>
            <td>
                <a href="${s.sponsor.website}">
                    ${s.sponsor.website}
                </a>
            </td>
            <td>
                ${s.contributionType}
            </td>
        </tr>
    </g:each>
</table>