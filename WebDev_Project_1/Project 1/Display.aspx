<%@ Page Title="Display" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="Display.aspx.cs" Inherits="Project_1.Display" culture="auto" meta:resourcekey="PageResource1" uiculture="auto" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
    <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

    <script>
         $(function() {
            $( "#accordion" ).accordion();
         });
      </script>

    <style>
        td {
            padding: 0 15px;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="status" runat="server" meta:resourcekey="statusResource1" />

    <div id="accordion" style="width: 100%;">
        <h3>GridView</h3>
        <div>
            <asp:GridView ID="gvUsers" runat="server" CellPadding="10" ForeColor="#333333" GridLines="None" AutoGenerateColumns="False" DataKeyNames="IDPerson" OnRowEditing="gvUsers_RowEditing" OnSelectedIndexChanging="gvUsers_SelectedIndexChanging" OnRowCancelingEdit="gvUsers_RowCancelingEdit" OnRowDataBound="gvUsers_RowDataBound" OnRowUpdating="gvUsers_RowUpdating" meta:resourcekey="gvUsersResource1">
                <Columns>
                    <asp:BoundField DataField="FName" HeaderText="First Name" meta:resourcekey="BoundFieldResource1" />
                    <asp:BoundField DataField="LName" HeaderText="Last Name" meta:resourcekey="BoundFieldResource2" />
                    <asp:BoundField DataField="Email" HeaderText="Email" meta:resourcekey="TemplateFieldResource1" />
                    <asp:BoundField DataField="Phone" HeaderText="Phone" meta:resourcekey="BoundFieldResource3" />
                    <asp:TemplateField HeaderText="Role">
                      <ItemTemplate>
                        <asp:DropDownList Enabled="false" ID="ddlRole" runat="server" DataTextField="Name" DataValueField="IDRole" />   <%--SelectedValue='<%# Bind ("RoleID") %>' DataTextField="Name" DataValueField="IDRole"--%>
                      </ItemTemplate> 
                    </asp:TemplateField>
                    <asp:CommandField CancelText="Cancel" DeleteText="Delete" EditText="Edit" SelectText="Select" ShowEditButton="True" ShowSelectButton="False" meta:resourcekey="CommandFieldResource1" />
                </Columns>
            </asp:GridView>
        </div>

        <h3>Repeater</h3>
        <div>
            <asp:Repeater ID="repeaterUsers" runat="server">
                <HeaderTemplate>
                    <table cellpadding="10">
                        <tr style="border-bottom: 2px solid black;">
                            <th bgcolor="#333333"><font color="#fff">First Name</font></th>
                            <th bgcolor="#333333"><font color="#fff">Last Name</font></th>
                            <th bgcolor="#333333"><font color="#fff">Email</font></th>
                            <th bgcolor="#333333"><font color="#fff">Phone</font></th>
                            <th bgcolor="#333333"><font color="#fff">Role</font></th>
                            <th bgcolor="#333333"><font color="#fff">City</font></th>
                            <th bgcolor="#333333"></th>
                        </tr>
            
                </HeaderTemplate>
                <ItemTemplate>
                    <tr>
                        <td><%# Eval("FName") %></td>
                        <td><%# Eval("LName") %></td>
                        <td><%# Eval("Email") %></td>
                        <td><%# Eval("Phone") %></td>
                        <td><%# GetRoleStr((int)Eval("RoleID")) %></td>
                        <td><%# GetCityStr((int)Eval("CityID")) %></td>

                        <td>
                            <asp:LinkButton ID="btnEditRep" OnClick="btnEditRep_Click" Visible='<%# IsEditEnabled() %>'
                                CommandArgument='<%# Eval("IDPerson") %>' runat="server" meta:resourcekey="btnEditRepResource1">Edit</asp:LinkButton>
                        </td>
                    </tr>
                </ItemTemplate>
                <FooterTemplate>
                    </table>
                </FooterTemplate>
            </asp:Repeater>
         </div>
    </div>

</asp:Content>





<%--<asp:TemplateField HeaderText="Email" meta:resourcekey="TemplateFieldResource1">
                <EditItemTemplate>
                    <asp:TextBox ID="txtEmail" runat="server" Text='<%# Bind("Email") %>' meta:resourcekey="txtEmailResource1"></asp:TextBox>
                    <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" ControlToValidate="txtEmail" Display="Dynamic" ErrorMessage="Email is wrong!" Font-Bold="True" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" meta:resourcekey="RegularExpressionValidator1Resource1"></asp:RegularExpressionValidator>
                </EditItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="lblEmail" runat="server" Text='<%# Bind("Email") %>' meta:resourcekey="lblEmailResource1"></asp:Label>
                </ItemTemplate>
            </asp:TemplateField>--%>
