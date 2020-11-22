using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Eventager.Startup))]
namespace Eventager
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
