<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
      <html>
        <head>
          <title>Application Users</title>
        </head>
        <body>
          <table>
            <thead>
              <tr>
                <th>First Name</th>
                <th>Last Name</th>
              </tr>
            </thead>
            <tbody>
              <xsl:apply-templates select="applicationUsers/user" />
            </tbody>
          </table>
        </body>
      </html>
    </xsl:template>
 
  <xsl:template match="user">
    <tr>
      <td>
        <xsl:value-of select="@firstName"/>
      </td>
      <td>
        <xsl:value-of select="@lastName"/>
      </td>
    </tr>
  </xsl:template>
</xsl:stylesheet>