<%@ Control Language="C#" AutoEventWireup="true" ClassName="Edit" CodeBehind="EditControl.ascx.cs" Inherits="Project_1.EditControl" %>

<style type="text/css">
    .auto-style1 {
        width: 100%;
    }
    .auto-style2 {
        width: 115px;
    }
    .editForm{
        border: 1px #cccccc solid;
        padding: 5px;
        margin: 10px;
        width: 350px;
        display: inline-block;
    }

    td {
            padding: 5px;
    }

    .btnEdit{
        color: white;
        background-color: steelblue;
    }

    .btnDelete{
        color: white;
        background-color: darkorange;
    }
</style>

 <asp:Label ID="status" runat="server" meta:resourcekey="statusResource1" />

<div class="editForm" style="text-align: left;">
<table cellpadding="5" cellspacing="0" class="auto-style1">
    <tr>
        <td class="auto-style2" style="text-align: right;">
            <asp:Label ID="Label1" runat="server" Text="First Name:"></asp:Label>
        </td>
        <td>
            <asp:TextBox ID="txtFName" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" Display="Dynamic" 
                                ControlToValidate="txtFName"  
                                ErrorMessage="First Name is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator1Resource1">*</asp:RequiredFieldValidator>

        </td>
    </tr>
    <tr>
        <td class="auto-style2" style="text-align: right;">
            <asp:Label ID="Label3" runat="server" Text="Last Name:"></asp:Label>
        </td>
        <td>
            <asp:TextBox ID="txtLName" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" Display="Dynamic" 
                                ControlToValidate="txtLName"  
                                ErrorMessage="First Name is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator1Resource1">*</asp:RequiredFieldValidator>
        </td>
    </tr>
    <tr>
        <td class="auto-style2" style="text-align: right;">
            <asp:Label ID="Label2" runat="server" Text="E-mail:"></asp:Label>
        </td>
        <td>
            <asp:TextBox ID="txtEmail" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" Display="Dynamic" ControlToValidate="txtEmail" 
                                                ErrorMessage="Email is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator3Resource1">*</asp:RequiredFieldValidator>
                <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" 
                                                ControlToValidate="txtEmail" Display="Dynamic" 
                                                ErrorMessage="E-mail address is incorrect!" ForeColor="Red" 
                                                ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" meta:resourcekey="RegularExpressionValidator1Resource1">Wrong Email</asp:RegularExpressionValidator>
        </td>
    </tr>
    <tr>
        <td class="auto-style2" style="text-align: right;">
            <asp:Label ID="Label4" runat="server" Text="Phone:"></asp:Label>
        </td>
        <td>
            <asp:TextBox ID="txtPhone" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" Display="Dynamic" 
                                ControlToValidate="txtPhone"  
                                ErrorMessage="First Name is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator1Resource1">*</asp:RequiredFieldValidator>
        </td>
    </tr>
    <tr>
        <td class="auto-style2" style="text-align: right;">
            <asp:Label ID="Label5" runat="server" Text="Password:"></asp:Label>
        </td>
        <td>
            <asp:TextBox ID="txtPassword" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" Display="Dynamic" 
                                ControlToValidate="txtPassword"  
                                ErrorMessage="First Name is required!" ForeColor="Red" meta:resourcekey="RequiredFieldValidator1Resource1">*</asp:RequiredFieldValidator>
        </td>
    </tr>
    <tr>
        <td class="auto-style2" style="text-align: right;">
            <asp:Label ID="Label6" runat="server" Text="Status:"></asp:Label>
        </td>
        <td>
            <asp:DropDownList DataValueField="IDRole" DataTextField="Name" runat="server" ID="ddlStatus">
            </asp:DropDownList>
        </td>
    </tr>
    <tr>
        <td class="auto-style2" style="text-align: right;">
            <asp:Label ID="Label7" runat="server" Text="City:"></asp:Label>
        </td>
        <td>
            <asp:DropDownList DataValueField="IDCity" DataTextField="Name" runat="server" ID="ddlCity">
            </asp:DropDownList>
        </td>
    </tr>
    
    <tr>
        <td class="auto-style2" style="text-align: right;">&nbsp;
            <asp:Button ID="btnEdit" CssClass="btnEdit" runat="server" OnClick="btnEdit_Click" Text="Edit" />
        </td>
        <td>
            <asp:Button ID="btnDelete" CssClass="btnDelete" runat="server" OnClick="btnDelete_Click" Text="Delete" />
        </td>
    </tr>
</table>
</div>
