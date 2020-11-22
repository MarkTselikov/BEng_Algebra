<%@ Page Title="Edit Data" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="EditUser.aspx.cs" Inherits="Project_1.EditUser" %>
<%@ Reference Control="~/Controls/EditControl.ascx" %>
<%@ Register src="Controls/EditControl.ascx" tagname="Edit" tagprefix="ec" %>


<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="status" runat="server" />
    <ec:Edit ID="editCtrl" runat="server" />
</asp:Content>
