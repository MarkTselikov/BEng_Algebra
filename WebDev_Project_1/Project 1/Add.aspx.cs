using Project_1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Project_1
{
    public partial class Add : CustomPage
    {
        List<TextBox> emails = new List<TextBox>();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (CustomPage.Login.RoleID != 2)
                Response.Redirect("Display.aspx");

            ddlCity.DataSource = repo.GetAllCities();
            ddlCity.DataBind();

            ddlStatus.DataSource = repo.GetAllRoles();
            ddlStatus.DataBind();

            emails.Add(txtEmail);
        }

        protected void btnCreate_Click(object sender, EventArgs e)
        {
            List<string> ems = new List<string>();
            foreach (var email in emails)
            {
                string value = email.Text;
                if(value != "")
                    ems.Add(value);
            }

            City city = null;
            Role role = null;

            foreach (var c in repo.GetAllCities())
            {
                if (c.Name == ddlCity.SelectedValue)
                    city = c;
            }

            foreach (var r in repo.GetAllRoles())
            {
                if (r.Name == ddlStatus.SelectedValue)
                    role = r;
            }

            Person p = new Person
            {
                FName = txtFName.Text,
                LName = txtLName.Text,
                Password = txtPassword.Text,
                Email = ems[0],
                Phone = txtPhone.Text,
                CityID = city.IDCity,
                RoleID = role.IDRole
            };

            txtFName.Text = "";
            txtLName.Text = "";
            txtPassword.Text = "";
            txtPasswordConf.Text = "";
            txtPhone.Text = "";
            txtEmail.Text = "";

            repo.CreatePerson(p);
        }

        protected void btnAddEmail_Click(object sender, EventArgs e)
        {
            if(emails.Count < 3)
            {
                TextBox tb = new TextBox();
                tb.Attributes.Add("Class", "form-control");
                emails.Add(tb);
                phEmail.Controls.Add(tb);
            }
        }
    }
}