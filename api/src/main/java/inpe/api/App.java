package inpe.api;

import static spark.Spark.*;
import spark.utils.IOUtils;
import static spark.Spark.get;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App  
{
    public static void main( String[] args )
    {

//		port(Integer.valueOf(System.getenv("PORT")));
		staticFileLocation("/public");
		// int a = 1;
		
		
		get("/hello", (req, res) -> {
			//envio de imagem
			try {
				String path = "/home/inpe/Imagens/image.fits";
			   Path p = Paths.get(path);

			   InputStream is = new FileInputStream(p.toFile());

			   res.raw().setContentType(Files.probeContentType(p));
			   res.raw().setHeader("Content-Disposition", "inline; filename=" + p.getFileName());

			   byte[] buffer = new byte[1024];
			   int len;
			   while ((len = is.read(buffer)) > 0) {
			      res.raw().getOutputStream().write(buffer, 0, len);
			   }

			   String result = IOUtils.toString(is);
			   is.close();
			   return result.trim();
			} catch (Exception e) { // handle the exceptions the way you want
			//   logger.error("Error while getting resource", e);
			   res.status(500);
			   return "";
			}
		});
		// get("/Images",(req,res) -> {});

		post("/readMessages", (req, res) ->  {

//			String str = new String(req.bodyAsBytes(), "UTF-8");
//			JSONObject j = new JSONObject(str);
//			String name = "";
//			pala = str;
//			name = j.getJSONObject("message").getString("text");
//			String chat = Integer.toString(j.getJSONObject("message").getJSONObject("chat").getInt("id"));
//			
//			ApiTest ap = new ApiTest(name);
			
		//	String wordTranslate = ap.readMessages();
		//	String link = ap.getAudio();
//			
//			OcApiSample oca = new OcApiSample();
//			
//			oca.Pegar(ap.apiNova(),chat);
//			
//			Map<String, Object> attributes = new HashMap<>();
//
//			URL myURL = new URL("https://api.telegram.org/bot145819752:AAHNF_sDGL20BVbCxGjVu-CGDLDHglWWlY8/sendMessage?chat_id=" + "&text=" );
//			
//			URLConnection yc = myURL.openConnection();
//			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
//			
//			String inputLine;
//			while ((inputLine = in.readLine()) != null)
//				attributes.put("message", inputLine);
//			in.close();
			return "";

		});
		
		
//
//		get("/db", (req, res) -> {
//			Connection connection = null;
//			Map<String, Object> attributes = new HashMap<>();
//
//			try {
//				connection = DatabaseUrl.extract().getConnection();
//
//				Statement stmt = connection.createStatement();
//				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//				stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//				ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//				ArrayList<String> output = new ArrayList<String>();
//				output.add(pala);
//				while (rs.next()) {
//					output.add("Read from DB: " + rs.getTimestamp("tick"));
//				}
//
//				attributes.put("results", output);
//			
//				return new ModelAndView(attributes, "db.ftl");
//			} catch (Exception e) {
//				attributes.put("message", "There was an error: " + e);
//				return new ModelAndView(attributes, "error.ftl");
//			} finally {
//				if (connection != null)
//					try {
//						connection.close();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			}
//		} , new FreeMarkerEngine());

	
    }
}