using Project_1.Models;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading;
using System.Web;

namespace Project_1
{
    public class CustomPage : System.Web.UI.Page
    {
        protected IRepo repo;
        public static Person Login { get; set; }

        protected override void OnPreInit(EventArgs e)
        {
            base.OnPreInit(e);

            if (Login == null)
                Response.Redirect("~/Login.aspx");

            if (Request.Cookies["theme"] != null)
            {
                var theme = Request.Cookies["theme"].Value;
                if (theme != "0")
                    this.Theme = theme;
            }

            if (Request.Cookies["repo"] == null)
            {
                HttpCookie cookie = new HttpCookie("repo");
                cookie.Value = "sql";
                Response.Cookies.Add(cookie);
            }
            repo = RepoFactory.GetRepo(Request.Cookies["repo"].Value);
        }

        protected override void InitializeCulture()
        {
            base.InitializeCulture();

            if (Request.Cookies["lang"] != null)
            {
                var lang = Request.Cookies["lang"].Value;
                if (lang != "0")

                    Thread.CurrentThread.CurrentCulture = new CultureInfo(lang);

                Thread.CurrentThread.CurrentUICulture = new CultureInfo(lang);
            }
        }
    }
}