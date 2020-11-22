using Project_1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Project_1
{
    public partial class Login : System.Web.UI.Page
    {
        IRepo repo = new Repo();

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnLogIn_Click(object sender, EventArgs e)
        {
            int isCorrect = repo.CheckLogin(email.Text, password.Text);

            if (isCorrect == 1)
            {
                Person p = repo.GetUserByEmail(email.Text);
                CustomPage.Login = p;

                HttpCookie cookie = new HttpCookie("user");
                cookie.Value = email.Text;
                if(rememberMe.Checked)
                    cookie.Expires = DateTime.Now.AddSeconds(1000);
                else
                    cookie.Expires = DateTime.Now.AddDays(1);
                Response.Cookies.Add(cookie);

                HttpCookie cookieRole = new HttpCookie("role");
                cookieRole.Value = repo.GetRole(p.RoleID).Name;
                if (rememberMe.Checked)
                    cookieRole.Expires = DateTime.Now.AddSeconds(1000);
                else
                    cookieRole.Expires = DateTime.Now.AddDays(1);
                Response.Cookies.Add(cookieRole);

                Response.Redirect("~/Display.aspx");
            }
            else
                lblStatus.Text = "* Wrong username or password";
        }
    }
}