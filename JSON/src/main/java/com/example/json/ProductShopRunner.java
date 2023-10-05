package com.example.json;

import com.example.json.Services.ProductsService;
import com.example.json.Services.SeedService;
import com.example.json.Services.UserService;
import com.example.json.entities.categories.CategoryStatsDTO;
import com.example.json.entities.categories.XMLCategoryStatsDTO;
import com.example.json.entities.categories.XMLCategoryStatsList;
import com.example.json.entities.products.ProductWithoutBuyerDTO;
import com.example.json.entities.users.UserWithSoldProductsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductsService productsService;
    private final UserService userService;

    private final Gson gson;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//       this.seedService.seedAll();

//        productsBetweenPriceWithoutBuyer();

//        getUsersWithSoldProducts();

//        getCategoryStats();


//        usersWithSoldProducts();


        xmlMarshall();
    }

    private void usersWithSoldProducts() {
        String json = this.gson.toJson(this.userService.getUsersWithSoldProductsOrderByCount());
        System.out.println(json);
    }


    private void xmlDemo() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "      <category>\n" +
                "         <name>Drugs</name>\n" +
                "         <product-count>68</product-count>\n" +
                "         <averagePrice>836.952941</averagePrice>\n" +
                "         <totalRevenue>56912.80</totalRevenue>\n" +
                "      </category>";

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        XMLCategoryStatsDTO result = (XMLCategoryStatsDTO)
                unmarshaller.unmarshal(inputStream);

        System.out.println(result);
    }

    private void xmlMarshall() throws JAXBException, IOException {
        List<XMLCategoryStatsDTO> xmlResult =
                this.productsService
                        .getCategoryStatistics()
                        .stream()
                        .map(XMLCategoryStatsDTO::new)
                        .collect(Collectors.toList());

        XMLCategoryStatsList xmlCategoryStatsList =
                new XMLCategoryStatsList(xmlResult);

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        String filePath = "src/main/resources/result.txt";

        FileWriter writer = new FileWriter(filePath);
            marshaller.marshal(xmlCategoryStatsList, writer);

    }


    private void getCategoryStats() {
        List<CategoryStatsDTO> result = this.productsService.getCategoryStatistics();

        String json = this.gson.toJson(result);
        System.out.println(json);
    }

    private void getUsersWithSoldProducts() {
        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();

        String json = this.gson.toJson(usersWithSoldProducts);

        System.out.println(json);
    }

    private void productsBetweenPriceWithoutBuyer() {
        List<ProductWithoutBuyerDTO> productsForSell = this.productsService.getProductsInPriceRangeForSell(500, 1000);

        String json = this.gson.toJson(productsForSell);

        System.out.println(json);
    }
}
