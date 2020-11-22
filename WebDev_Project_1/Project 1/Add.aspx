<%@ Page Title="Add Person" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="Add.aspx.cs" Inherits="Project_1.Add" culture="auto" meta:resourcekey="PageResource1" uiculture="auto" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div style="width: 800px;" class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4">
                <asp:Label Text="First Name:" runat="server" meta:resourcekey="LabelResource1" />
                <asp:TextBox ID="txtFName" runat="server" CssClass="form-control" meta:resourcekey="txtFNameResource1" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" Display="Dynamic" 
                                                ControlToValidate="txtFName"  
                                                ErrorMessage="First Name is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator1Resource1">*</asp:RequiredFieldValidator>

                <asp:Label Text="Last Name:" runat="server" meta:resourcekey="LabelResource2" />
                <asp:TextBox ID="txtLName" runat="server" CssClass="form-control" meta:resourcekey="txtLNameResource1" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" Display="Dynamic" ControlToValidate="txtLName" 
                                                ErrorMessage="Last Name is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator2Resource1">*</asp:RequiredFieldValidator>

                <asp:PlaceHolder ID="phEmail" runat="server">
                    <asp:Label Text="Email:" runat="server" meta:resourcekey="LabelResource3" />
                </asp:PlaceHolder>
                <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" meta:resourcekey="txtEmailResource1" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" Display="Dynamic" ControlToValidate="txtEmail" 
                                                ErrorMessage="Email is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator3Resource1">*</asp:RequiredFieldValidator>
                <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" 
                                                ControlToValidate="txtEmail" Display="Dynamic" 
                                                ErrorMessage="E-mail address is incorrect!" ForeColor="Red" 
                                                ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" meta:resourcekey="RegularExpressionValidator1Resource1">*</asp:RegularExpressionValidator>
                <asp:Button Text="More Emails" runat="server" ID="btnAddEmail" OnClick="btnAddEmail_Click" meta:resourcekey="btnAddEmailResource1" />

            </div>
            <div class="col-lg-4 col-md-4 col-sm-4">
                <asp:Label Text="Phone:" runat="server" meta:resourcekey="LabelResource4" />
                <asp:TextBox ID="txtPhone" runat="server" CssClass="form-control" meta:resourcekey="txtPhoneResource1" />

                <asp:Label Text="City:" runat="server" meta:resourcekey="LabelResource5" />
                <asp:DropDownList ID="ddlCity" runat="server" CssClass="form-control" meta:resourcekey="ddlCityResource1">
                </asp:DropDownList>

                <asp:Label Text="Status:" runat="server" meta:resourcekey="LabelResource6" />
                <asp:DropDownList ID="ddlStatus" runat="server" CssClass="form-control" meta:resourcekey="ddlStatusResource1">
                </asp:DropDownList>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4">
                <asp:Label Text="Password:" runat="server" meta:resourcekey="LabelResource7" />
                <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" CssClass="form-control" meta:resourcekey="txtPasswordResource1" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" Display="Dynamic" ControlToValidate="txtPassword" 
                                                ErrorMessage="Password is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator4Resource1">*</asp:RequiredFieldValidator>

                <asp:Label Text="Confirm Password:" runat="server" meta:resourcekey="LabelResource8" />
                <asp:TextBox ID="txtPasswordConf" runat="server" TextMode="Password" CssClass="form-control" meta:resourcekey="txtPasswordConfResource1" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" Display="Dynamic" ControlToValidate="txtPasswordConf" 
                                                ErrorMessage="You need to confirm the password!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator5Resource1">*</asp:RequiredFieldValidator>
            
                <asp:Button Text="Create" ID="btnCreate" runat="server" OnClick="btnCreate_Click" meta:resourcekey="btnCreateResource1" />
            
                <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToCompare="txtPasswordConf" ControlToValidate="txtPassword" Display="Dynamic" ErrorMessage="Passwords don't match!" ForeColor="Red" meta:resourcekey="CompareValidator1Resource1">*</asp:CompareValidator>
        
                <asp:ValidationSummary ID="ValidationSummary1" runat="server" 
                                                 HeaderText="Notification: " meta:resourcekey="ValidationSummary1Resource1" />
             </div>
         </div>
    </div>
</asp:Content>
