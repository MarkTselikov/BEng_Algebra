using Project_1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Project_1
{
    public partial class Edit : CustomPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (CustomPage.Login.RoleID != 2)
                Response.Redirect("Display.aspx");

            EditControl ec = LoadControl("Controls/EditControl.ascx") as EditControl;

            foreach (Person p in repo.GetAllPeople())
            {
                var editControl = (EditControl)Page.LoadControl("Controls/EditControl.ascx");
                editControl.InitForm(p);
                content.Controls.Add(editControl);
            }
        }
    }
}