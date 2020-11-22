using Project_1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Project_1
{
    public partial class Main : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            txtTitle.Text = Page.Title;
            if (CustomPage.Login != null)
                lbLogin.Text = $"{CustomPage.Login.Email}";
            else
            {
                lbLogin.Visible = false;
                lbLogout.Visible = false;
            }

            switch (Page.Title)
            {
                case "Settings":
                    linkSettings.Style.Add("background-color", "steelblue");
                    linkSettings.Style.Add("color", "white");
                    break;
                case "Add Person":
                    linkAdd.Style.Add("background-color", "steelblue");
                    linkAdd.Style.Add("color", "white");
                    break;
                case "Edit":
                    linkEdit.Style.Add("background-color", "steelblue");
                    linkEdit.Style.Add("color", "white");
                    break;
                case "Display":
                    linkDisplay.Style.Add("background-color", "steelblue");
                    linkDisplay.Style.Add("color", "white");
                    break;
                default:
                    break;
            }

            if(Request.Cookies["repo"] != null)
                txtRepoType.Text = $"Repository - {Request.Cookies["repo"].Value}";
        }

        protected void lbLogout_Click(object sender, EventArgs e)
        {
            Response.Cookies["user"].Expires = DateTime.Now.AddDays(-1d);
            Session.Abandon();
            Response.Cookies.Clear();

            CustomPage.Login = null;

            HttpCookie cookie = new HttpCookie("user");
            cookie.Value = null;
            Response.Cookies.Add(cookie);

            Response.Redirect("~/Login.aspx");
        }
    }
}