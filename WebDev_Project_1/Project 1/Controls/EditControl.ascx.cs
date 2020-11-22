using Project_1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Project_1
{
    public partial class EditControl : System.Web.UI.UserControl
    {
        int id = 1;
        IRepo repo;

        protected void Page_Load(object sender, EventArgs e)
        {
            repo = RepoFactory.GetRepo(Request.Cookies["repo"].Value);

            //ddlCity.DataSource = repo.GetAllCities();
            //ddlStatus.DataSource = repo.GetAllRoles();

            //ddlCity.DataBind();
            //ddlStatus.DataBind();

            if(!IsPostBack) { 
                foreach (var c in repo.GetAllCities())
                {
                    ddlCity.Items.Add(new ListItem(c.Name, c.IDCity.ToString()));
                }

                foreach (var r in repo.GetAllRoles())
                {
                    ddlStatus.Items.Add(new ListItem(r.Name, r.IDRole.ToString()));
                }
            }
        }


        public void InitForm(Person p)
        {
            id = p.IDPerson;

            txtFName.Text = p.FName;
            txtLName.Text = p.LName;
            txtEmail.Text = p.Email;
            txtPassword.Text = p.Password;
            txtPhone.Text = p.Phone;
            txtFName.Text = p.FName;

            //ddlCity.Items.FindByValue(p.CityID.ToString()).Selected = true;
            //ddlStatus.Items.FindByValue(p.RoleID.ToString()).Selected = true;

            //ddlCity.SelectedIndex = p.CityID;
            //ddlStatus.SelectedIndex = p.RoleID;

            //ddlCity.SelectedIndex =
            //     ddlCity.Items.IndexOf(ddlCity.Items.FindByValue(p.CityID.ToString()));

        }

        public void InitForm(Person p, IRepo repo)
        {
            txtFName = new TextBox();
            txtLName = new TextBox();
            txtEmail = new TextBox();
            txtPassword = new TextBox();
            txtPhone = new TextBox();

            id = p.IDPerson;

            txtFName.Text = p.FName;
            txtLName.Text = p.LName;
            txtEmail.Text = p.Email;
            txtPassword.Text = p.Password;
            txtPhone.Text = p.Phone;
            txtFName.Text = p.FName;
        }


         protected void btnEdit_Click(object sender, EventArgs e)
         {
            Person p = new Person
            {
                IDPerson = id,
                FName = txtFName.Text,
                LName = txtLName.Text,
                Email = txtEmail.Text,
                Phone = txtPhone.Text,
                Password = txtPassword.Text,
                CityID = int.Parse(ddlCity.SelectedValue),
                RoleID = int.Parse(ddlStatus.SelectedValue)
            };

            repo.UpdatePerson(p);
         }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            repo.DeletePerson(id);
            Response.Redirect(Request.Url.AbsolutePath);
        }
    }
}