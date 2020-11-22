using Microsoft.ApplicationBlocks.Data;
using Project.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;

public class Repo
{
    public static DataSet ds { get; set; }
    private static string cs = ConfigurationManager.ConnectionStrings["cs"].ConnectionString;

    public static Buyer GetBuyer(int buyerID)
    {
        return GetBuyers().Single(b => b.IDBuyer == buyerID);
    }

    public static City GetCity(int cityID)
    {
        DataRow row = SqlHelper.ExecuteDataset(cs, "GetCity", cityID).Tables[0].Rows[0];
        return new City
        {
            IDCity = (int)row["IDGrad"],
            Name = row["Naziv"].ToString()
        };
    }

    public static Commercial GetCommercial(int comID)
    {
        DataRow row = SqlHelper.ExecuteDataset(cs, "GetCommercial", comID).Tables[0].Rows[0];
        return new Commercial
        {
            IDCommercial = (int)row["IDKomercijalist"],
            FName = row["Ime"].ToString(),
            LName = row["Prezime"].ToString(),
            PermEmployee = (bool)row["StalniZaposlenik"]
        };
    }

    public static Product GetProduct(int productID)
    {
        DataRow row = SqlHelper.ExecuteDataset(cs, "GetProduct", productID).Tables[0].Rows[0];
        return new Product
        {
            IDProduct = (int)row["IDProizvod"],
            Name = row["Naziv"].ToString(),
            Number = row["BrojProizvoda"].ToString(),
            Color = row["Boja"].ToString(),
            MinAmount = (short)row["MinimalnaKolicinaNaSkladistu"],
            Price = (decimal)row["CijenaBezPDV"],
            SubcategoryID = (int)row["PotkategorijaID"]
        };
    }

    public static Bill GetBill(int billID)
    {
        DataSet ds = SqlHelper.ExecuteDataset(cs, "GetBuyers");
        DataRow row = ds.Tables[0].Rows[0];

        return new Bill
        {
            IDBill = (int)row["IDRacun"],
            IssueDate = (DateTime)row["DatumIzdavanja"],
            BillNumber = row["BrojRacuna"].ToString(),
            BuyerID = (int)row["KupacID"],
            CommercialID = (int)row["KomercijalistID"],
            CreditCardID = (int)row["KreditnaKarticaID"],
            Comment = row["Komentar"].ToString()
        };
    }

    public static State GetState(int cityID)
    {
        DataRow row = SqlHelper.ExecuteDataset(cs, "GetState", cityID).Tables[0].Rows[0];

        return new State
        {
            IDState = (int)row["IDDrzava"],
            Name = row["Naziv"].ToString()
        };
    }

    public static IEnumerable<Bill> GetBuyerBills(int buyerID)
    {
        DataSet ds = SqlHelper.ExecuteDataset(cs, "GetBuyerBills", buyerID);

        foreach (DataRow row in ds.Tables[0].Rows)
        {
            yield return new Bill
            {
                IDBill = (int)row["IDRacun"],
                IssueDate = (DateTime)row["DatumIzdavanja"],
                BillNumber = row["BrojRacuna"].ToString(),
                BuyerID = buyerID,
                CommercialID = (int)row["KomercijalistID"],
                CreditCardID = (int)row["KreditnaKarticaID"],
                Comment = row["Komentar"].ToString()
            };
        }
    }

    public static IEnumerable<Item> GetItemsByBill(int billID)
    {
        DataSet ds = SqlHelper.ExecuteDataset(cs, "GetItemsByBill", billID);

        foreach (DataRow row in ds.Tables[0].Rows)
        {
            yield return new Item
            {
                IDItem = (int)row["IDStavka"],
                BillID = (int)row["RacunID"],
                Amount = (short)row["Kolicina"],
                ProductID = (int)row["ProizvodID"],
                Price = (decimal)row["CijenaPoKomadu"],
                Discount = (decimal)row["PopustUPostocima"],
                TotalPrice = (decimal)row["UkupnaCijena"]
            };
        }
    }

    public static IEnumerable<Buyer> GetBuyers()
    {
        DataSet ds = SqlHelper.ExecuteDataset(cs, "GetBuyers");

        foreach (DataRow row in ds.Tables[0].Rows)
        {
            yield return new Buyer
            {
                IDBuyer = (int)row["IDKupac"],
                FName = row["Ime"].ToString(),
                LName = row["Prezime"].ToString(),
                Email = row["Email"].ToString(),
                Phone = row["Telefon"].ToString(),
                CityID = (int)row["GradID"]
            };
        }
    }

    public static IEnumerable<City> GetCities()
    {
        DataSet ds = SqlHelper.ExecuteDataset(cs, "GetCities");

        foreach (DataRow row in ds.Tables[0].Rows)
        {
            yield return new City
            {
                IDCity = (int)row["IDGrad"],
                Name = row["Naziv"].ToString(),
                StateID = (int)row["DrzavaID"]
            };
        }
    }

    public static int UpdateBuyer(Buyer b)
    {
        return SqlHelper.ExecuteNonQuery(cs, "UpdateBuyer",
                b.IDBuyer,
                b.FName,
                b.LName,
                b.Email,
                b.Phone,
                b.CityID
        );
    }

    public static int CreateBuyer(Buyer b)
    {
        return SqlHelper.ExecuteNonQuery(cs, "CreateBuyer",
                b.FName,
                b.LName,
                b.Email,
                b.Phone,
                b.CityID
        );
    }
}
