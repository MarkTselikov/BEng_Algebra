using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Project_1
{
    public partial class Settings : CustomPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void DDLTheme_SelectedIndexChanged(object sender, EventArgs e)
        {
            HttpCookie cookie = new HttpCookie("theme");
            cookie.Value = DDLTheme.SelectedValue;
            Response.Cookies.Add(cookie);

            Response.Redirect(Request.Url.AbsolutePath);
        }

        protected void DDLLanguage_SelectedIndexChanged(object sender, EventArgs e)
        {
            HttpCookie cookie = new HttpCookie("lang");
            cookie.Value = DDLLanguage.SelectedValue;
            Response.Cookies.Add(cookie);

            // refresh
            Response.Redirect(Request.Url.AbsolutePath);
        }

        protected void ddlRepoMode_SelectedIndexChanged(object sender, EventArgs e)
        {
            HttpCookie cookie = new HttpCookie("repo");
            cookie.Value = ddlRepoMode.SelectedValue;
            Response.Cookies.Add(cookie);

            // refresh
            Response.Redirect(Request.Url.AbsolutePath);
        }
    }
}