using Project_1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Project_1
{
    public partial class Display : CustomPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                GridViewDataBind();
                RepeaterDataBind();
            }

            status.Text = Response.Cookies["user"].Value;
        }

        private void GridViewDataBind()
        {
            try
            {
                gvUsers.DataSource = repo.GetAllPeople();
                gvUsers.DataBind();
            }
            catch (Exception ex)
            {
                status.Text = $"Something went wrong: {ex.Message}";
            }
        }

        private void RepeaterDataBind()
        {
            try
            {
                repeaterUsers.DataSource = repo.GetAllPeople();
                repeaterUsers.DataBind();
            }
            catch (Exception ex)
            {
                status.Text = $"Something went wrong: {ex.Message}";
            }
        }

        public string GetRoleStr(int id)
        {
            return repo.GetRole(id).Name;
        }

        public string GetCityStr(int id)
        {
            return repo.GetCity(id).Name;
        }

        protected void gvUsers_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                var ddl = e.Row.FindControl("ddlRole") as DropDownList;
                if (ddl != null)
                {
                    ddl.DataSource = repo.GetAllRoles();
                    ddl.DataBind();
                }
            }
        }

        protected void gvUsers_RowEditing(object sender, GridViewEditEventArgs e)
        {
            gvUsers.EditIndex = e.NewEditIndex;
            GridViewDataBind();
            DropDownList ddlRole = gvUsers.Rows[e.NewEditIndex].FindControl("ddlRole") as DropDownList;
            ddlRole.Enabled = true;
            ddlRole.ClearSelection();
        }

        protected void gvUsers_SelectedIndexChanging(object sender, GridViewSelectEventArgs e)
        {
            int rowIndex = e.NewSelectedIndex;
            int PersonID = (int)gvUsers.DataKeys[rowIndex].Value;
        }

        protected void gvUsers_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e)
        {
            gvUsers.EditIndex = -1;
            GridViewDataBind();
        }

        protected void gvUsers_RowUpdating(object sender, GridViewUpdateEventArgs e)
        {
            GridViewRow updateRow = gvUsers.Rows[e.RowIndex];

            string fName = ((TextBox)updateRow.Cells[0].Controls[0]).Text;
            string lName = ((TextBox)updateRow.Cells[1].Controls[0]).Text;
            string email = ((TextBox)updateRow.Cells[2].Controls[0]).Text;
            string phone = ((TextBox)updateRow.Cells[3].Controls[0]).Text;
            int role = int.Parse(((DropDownList)updateRow.Cells[4].Controls[1]).SelectedValue);

            status.Text = role.ToString();

            Person p = new Person();
            p.FName = fName;
            p.LName = lName;
            p.Email = email;
            p.Phone = phone;
            p.IDPerson = (int)gvUsers.DataKeys[e.RowIndex].Value;
            p.RoleID = role;

            //p.RoleID = int.Parse(ddlRole.SelectedValue);

            try
            {
                repo.UpdatePersonGV(p);
            }
            catch(Exception ex)
            {
                status.Text = $"Something went wrong: {ex.Message}";
            }

            gvUsers.EditIndex = -1;
            GridViewDataBind();
        }

        protected void btnEditRep_Click(object sender, EventArgs e)
        {
            HttpCookie cookie = new HttpCookie("userToEdit");
            //cookie.Value = e.CommandArgument.ToString();
            cookie.Value = (sender as LinkButton).CommandArgument.ToString();
            Response.Cookies.Add(cookie);

            Response.Redirect("EditUser.aspx");
        }

        public bool IsEditEnabled()
        {
            if (CustomPage.Login.RoleID != 2)
                return false;
            return true;
        }
    }
}