<%@ Page Title="Settings" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="Settings.aspx.cs" Inherits="Project_1.Settings" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">


    <div style="width: 300px;">
        <asp:Label Text="Theme: " runat="server" ID="lblTheme" meta:resourcekey="lblThemeResource1" />
        <asp:DropDownList 
                runat="server" 
                ID="DDLTheme" 
                CssClass="form-control" 
                OnSelectedIndexChanged="DDLTheme_SelectedIndexChanged"
                AutoPostBack="True" meta:resourcekey="DDLThemeResource1">
            <asp:ListItem Text=" --- Select --- " Value="0" meta:resourcekey="liLangDefResourse" />
            <asp:ListItem Text="Default" Value="default" meta:resourcekey="liThemeDefResource2" />
            <asp:ListItem Text="Blue" Value="blue" meta:resourcekey="liThemeBlueResource3" />
            <asp:ListItem Text="Red" Value="red" meta:resourcekey="liThemeRedResource4" />
        </asp:DropDownList>

        <asp:Label Text="Theme: " runat="server" ID="lblLanguage" meta:resourcekey="lblLanguageResource1" />
        <asp:DropDownList 
                runat="server" 
                ID="DDLLanguage" 
                CssClass="form-control" 
                OnSelectedIndexChanged="DDLLanguage_SelectedIndexChanged"
                AutoPostBack="True" meta:resourcekey="DDLThemeResource1">
            <asp:ListItem Text=" --- Select --- " Value="0" meta:resourcekey="liLangDefResourse" />
            <asp:ListItem Text="English" Value="en" meta:resourcekey="liLangEnResourse" />
            <asp:ListItem Text="Русский" Value="ru" meta:resourcekey="liLangRuResourse" />
        </asp:DropDownList>

        <asp:Label Text="Repository: " runat="server" ID="Label1" meta:resourcekey="lblRepo" />
        <asp:DropDownList 
                runat="server" 
                ID="ddlRepoMode" 
                CssClass="form-control" 
                OnSelectedIndexChanged="ddlRepoMode_SelectedIndexChanged"
                AutoPostBack="True" meta:resourcekey="ddlRepoResource">
            <asp:ListItem Text=" --- Select --- " Value="0" meta:resourcekey="liRepoDefResourse" />
            <asp:ListItem Text="SQL" Value="sql" meta:resourcekey="liRepoSqlResourse" />
            <asp:ListItem Text="Text" Value="txt" meta:resourcekey="liRepoTxtResourse" />
        </asp:DropDownList>
    </div>
</asp:Content>

