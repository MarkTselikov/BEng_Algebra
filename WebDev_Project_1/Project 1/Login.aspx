<%@ Page Title="Login" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="Project_1.Login" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        td {
            padding: 5px;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <table cellpadding="50">
        <tr>
            <td><asp:Label Text="E-mail:" runat="server" /></td>
            <td><asp:TextBox runat="server" ID="email" /></td>
        </tr>
        <tr>
            <td><asp:Label Text="Password:" runat="server" /></td>
            <td><asp:TextBox runat="server" ID="password" TextMode="Password" /></td>
        </tr>
        <tr>
            <td></td>
            <td><asp:CheckBox Text="Remember Me" runat="server" ID="rememberMe" /></td>
        </tr>
        <tr>
            <td></td>
            <td><asp:Button ID="btnLogIn" runat="server" Text="Log In" OnClick="btnLogIn_Click" /></td>
        </tr>
    </table>
    <asp:Label ID="lblStatus" Text="" ForeColor="Red" runat="server" />
</asp:Content>
