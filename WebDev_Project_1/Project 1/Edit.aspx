<%@ Page Title="Edit" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="Edit.aspx.cs" Inherits="Project_1.Edit" %>
<%@ Reference Control="~/Controls/EditControl.ascx" %>
<%@ Register src="Controls/EditControl.ascx" tagname="Edit" tagprefix="ec" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div>
        <asp:Label ID="status" runat="server" meta:resourcekey="statusResource1" />
        <asp:Panel ID="content" runat="server"></asp:Panel>
    
    </div>
</asp:Content>
