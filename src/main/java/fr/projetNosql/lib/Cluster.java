package fr.projetNosql.lib;



import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class Cluster {

    // Replace the placeholder with your MongoDB deployment's connection string
    private String uri = "mongodb+srv://johann:PEmuDpCycGt0Eco1@clusterprojetnosql.qwvo5gy.mongodb.net/";
    private AmazonDynamoDB client;
    private DynamoDB dynamoDb;

    public Cluster() {
        connect();
    }

    private void connect() {
        try {
            client  = AmazonDynamoDBClientBuilder.standard().build();
            dynamoDb = new DynamoDB(client);
            System.out.println("Connected to the database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    // Client
    public void insertClient(Client client) {
        Table table = dynamoDb.getTable("Client");
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String dateInString = "2019-10-05T20:15:00.000Z";
            Date date = dateFormatter.parse(dateInString);

            Item item = new Item()
                    .withPrimaryKey("id", client.getId())
                    .withString("email", client.getEmail())
                    .withString("nom", client.getNom())
                    .withString("prenom", client.getPrenom())
                    .withString("adresse", client.getAdresse())
                    .withString("codePostal", client.getCodePostal())
                    .withString("ville", client.getVille())
                    .withString("complement", client.getComplement())
                    .withString("telephone", client.getTelephone())
                    .withString("motDePasse", client.getMotDePasse())
                    .withString("dateInscription", dateInString);
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (ParseException e) {
            System.err.println("Unable to add client: " + client.getNom() + " " + client.getPrenom());
            System.err.println(e.getMessage());

        } catch (Exception e) {
            System.err.println("Unable to add client: " + client.getNom() + " " + client.getPrenom());
            System.err.println(e.getMessage());
        }

    }

    public void deleteClient(Client client) {
        Table table = dynamoDb.getTable("Client");
        try {
            table.deleteItem("id", client.getId());
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete client: " + client.getNom() + " " + client.getPrenom());
            System.err.println(e.getMessage());
        }
    }

    public void updateClient(Client client) {
        Table table = dynamoDb.getTable("Client");
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String dateInString = "2019-10-05T20:15:00.000Z";
            Date date = dateFormatter.parse(dateInString);

            Item item = new Item()
                    .withPrimaryKey("id", client.getId())
                    .withString("email", client.getEmail())
                    .withString("nom", client.getNom())
                    .withString("prenom", client.getPrenom())
                    .withString("adresse", client.getAdresse())
                    .withString("codePostal", client.getCodePostal())
                    .withString("ville", client.getVille())
                    .withString("complement", client.getComplement())
                    .withString("telephone", client.getTelephone())
                    .withString("motDePasse", client.getMotDePasse())
                    .withString("dateInscription", dateInString);
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (ParseException e) {
            System.err.println("Unable to update client: " + client.getNom() + " " + client.getPrenom());
            System.err.println(e.getMessage());

        } catch (Exception e) {
            System.err.println("Unable to update client: " + client.getNom() + " " + client.getPrenom());
            System.err.println(e.getMessage());
        }
    }

    public Client getOneClient(String email) {
        Table table = dynamoDb.getTable("Client");
        try {
            Item item = table.getItem("email", email);
            System.out.println("GetItem succeeded: " + item);
            return new Client(item.getString("id"), item.getString("email"), item.getString("nom"), item.getString("prenom"), item.getString("adresse"), item.getString("codePostal"), item.getString("ville"), item.getString("complement"), item.getString("telephone"), item.getString("motDePasse"));
        } catch (Exception e) {
            System.err.println("Unable to get client: " + email);
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Client> getAllClient() {
        Table table = dynamoDb.getTable("Client");
        ArrayList<Client> clients = new ArrayList<>();
        try {
            ItemCollection<ScanOutcome> items = table.scan();
            Iterator<Item> iterator = items.iterator();
            Item item = null;
            while (iterator.hasNext()) {
                item = iterator.next();
                clients.add(new Client(item.getString("id"), item.getString("email"), item.getString("nom"), item.getString("prenom"), item.getString("adresse"), item.getString("codePostal"), item.getString("ville"), item.getString("complement"), item.getString("telephone"), item.getString("motDePasse")));
            }
            return clients;
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //Type Bateau
    public void insertTypeBateau(TypeBateau typeBateau) {
        Table table = dynamoDb.getTable("TypeBateau");
        try {
            Item item = new Item()
                    .withPrimaryKey("idTypeBateau", typeBateau.getIdTypeBateau())
                    .withString("nomTypeBateau", typeBateau.getNomTypeBateau());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to add type bateau: " + typeBateau.getNomTypeBateau());
            System.err.println(e.getMessage());
        }
    }

    public void deleteTypeBateau(TypeBateau typeBateau) {
        Table table = dynamoDb.getTable("TypeBateau");
        try {
            table.deleteItem("idTypeBateau", typeBateau.getIdTypeBateau());
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete type bateau: " + typeBateau.getNomTypeBateau());
            System.err.println(e.getMessage());
        }
    }

    public void updateTypeBateau(TypeBateau typeBateau) {
        Table table = dynamoDb.getTable("TypeBateau");
        try {
            Item item = new Item()
                    .withPrimaryKey("idTypeBateau", typeBateau.getIdTypeBateau())
                    .withString("nomTypeBateau", typeBateau.getNomTypeBateau());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to update type bateau: " + typeBateau.getNomTypeBateau());
            System.err.println(e.getMessage());
        }
    }

    public TypeBateau getOneTypeBateau(String typeBateauID) {
        Table table = dynamoDb.getTable("TypeBateau");
        try {
            Item item = table.getItem("idTypeBateau", typeBateauID);
            System.out.println("GetItem succeeded: " + item);
            return new TypeBateau(item.getString("idTypeBateau"), item.getString("nomTypeBateau"));
        } catch (Exception e) {
            System.err.println("Unable to get type bateau: " + typeBateauID);
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<TypeBateau> getAllTypeBateau() {
        Table table = dynamoDb.getTable("TypeBateau");
        ArrayList<TypeBateau> typeBateaux = new ArrayList<>();
        try {
            ItemCollection<ScanOutcome> items = table.scan();
            Iterator<Item> iterator = items.iterator();
            Item item = null;
            while (iterator.hasNext()) {
                item = iterator.next();
                typeBateaux.add(new TypeBateau(item.getString("idTypeBateau"), item.getString("nomTypeBateau")));
            }
            return typeBateaux;
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //Bateau

    public void insertBateau(Bateau bateau) {
       Table table = dynamoDb.getTable("Bateau");
        try {
            Item item = new Item()
                    .withPrimaryKey("idBateau", bateau.getIdBateau())
                    .withInt("nbPlace", bateau.getNbPlace())
                    .withInt("caution", bateau.getCaution())
                    .withInt("puissanceMoteur", bateau.getPuissance())
                    .withString("idTypeBateau", bateau.getIdTypeBateau());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to add bateau: " + bateau.getIdBateau());
            System.err.println(e.getMessage());
        }
    }

    public void deleteBateau(Bateau bateau) {
        Table table = dynamoDb.getTable("Bateau");
        try {
            table.deleteItem("idBateau", bateau.getIdBateau());
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete bateau: " + bateau.getIdBateau());
            System.err.println(e.getMessage());
        }
    }

    public void updateBateau(Bateau bateau) {
        Table table = dynamoDb.getTable("Bateau");
        try {
            Item item = new Item()
                    .withPrimaryKey("idBateau", bateau.getIdBateau())
                    .withInt("nbPlace", bateau.getNbPlace())
                    .withInt("caution", bateau.getCaution())
                    .withInt("puissanceMoteur", bateau.getPuissance())
                    .withString("idTypeBateau", bateau.getIdTypeBateau());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to update bateau: " + bateau.getIdBateau());
            System.err.println(e.getMessage());
        }
    }

    public Bateau getOneBateau(String bateauID) {
        Table table = dynamoDb.getTable("Bateau");
        try {
            Item item = table.getItem("idBateau", bateauID);
            System.out.println("GetItem succeeded: " + item);
            return new Bateau(item.getString("idBateau"), item.getInt("nbPlace"), item.getInt("caution"), item.getInt("puissanceMoteur"), item.getString("idTypeBateau"), item.getString("nomTypeBateau"));
        } catch (Exception e) {
            System.err.println("Unable to get bateau: " + bateauID);
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Bateau> getAllBateau() {
        Table table = dynamoDb.getTable("Bateau");
        ArrayList<Bateau> bateaux = new ArrayList<>();
        try {
            ItemCollection<ScanOutcome> items = table.scan();
            Iterator<Item> iterator = items.iterator();
            Item item = null;
            while (iterator.hasNext()) {
                item = iterator.next();
                bateaux.add(new Bateau(item.getString("idBateau"), item.getInt("nbPlace"), item.getInt("caution"), item.getInt("puissanceMoteur"), item.getString("idTypeBateau"), item.getString("nomTypeBateau")));
            }
            return bateaux;
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //Skipper

    public void insertSkipper(Skipper skipper) {
       Table table = dynamoDb.getTable("Skipper");
        try {
            Item item = new Item()
                    .withPrimaryKey("idSkipper", skipper.getId())
                    .withString("nom", skipper.getNom())
                    .withString("prenom", skipper.getPrenom())
                    .withInt("tarif", skipper.getTarif());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to add skipper: " + skipper.getNom());
            System.err.println(e.getMessage());
        }
    }

    public void deleteSkipper(Skipper skipper) {
       Table table = dynamoDb.getTable("Skipper");
        try {
            table.deleteItem("idSkipper", skipper.getId());
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete skipper: " + skipper.getNom());
            System.err.println(e.getMessage());
        }
    }

    public void updateSkipper(Skipper skipper) {
       Table table = dynamoDb.getTable("Skipper");
        try {
            Item item = new Item()
                    .withPrimaryKey("idSkipper", skipper.getId())
                    .withString("nom", skipper.getNom())
                    .withString("prenom", skipper.getPrenom())
                    .withInt("tarif", skipper.getTarif());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to update skipper: " + skipper.getNom());
            System.err.println(e.getMessage());
        }
    }

    public Skipper getOneSkipper(String skipperID) {
         Table table = dynamoDb.getTable("Skipper");
          try {
                Item item = table.getItem("idSkipper", skipperID);
                System.out.println("GetItem succeeded: " + item);
                return new Skipper(item.getString("idSkipper"), item.getString("nom"), item.getString("prenom"), item.getInt("tarif"));
          } catch (Exception e) {
                System.err.println("Unable to get skipper: " + skipperID);
                System.err.println(e.getMessage());
                return null;
          }
    }

    public ArrayList<Skipper> getAllSkipper() {
        Table table = dynamoDb.getTable("Skipper");
        ArrayList<Skipper> skippers = new ArrayList<>();
        try {
            ItemCollection<ScanOutcome> items = table.scan();
            Iterator<Item> iterator = items.iterator();
            Item item = null;
            while (iterator.hasNext()) {
                item = iterator.next();
                skippers.add(new Skipper(item.getString("idSkipper"), item.getString("nom"), item.getString("prenom"), item.getInt("tarif")));
            }
            return skippers;
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //Promotion
    public void insertPromotion(Promotion promotion) {
       Table table = dynamoDb.getTable("Promotion");
        try {
            Item item = new Item()
                    .withPrimaryKey("idPromotion", promotion.getId())
                    .withString("nom", promotion.getCode())
                    .withInt("montant", promotion.getMontant());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to add promotion: " + promotion.getCode());
            System.err.println(e.getMessage());
        }
    }

    public void deletePromotion(Promotion promotion) {
        Table table = dynamoDb.getTable("Promotion");
        try {
            table.deleteItem("idPromotion", promotion.getId());
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete promotion: " + promotion.getCode());
            System.err.println(e.getMessage());
        }
    }

    public void updatePromotion(Promotion promotion) {
       Table table = dynamoDb.getTable("Promotion");
        try {
            Item item = new Item()
                    .withPrimaryKey("idPromotion", promotion.getId())
                    .withString("nom", promotion.getCode())
                    .withInt("montant", promotion.getMontant());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to update promotion: " + promotion.getCode());
            System.err.println(e.getMessage());
        }
    }

    public Promotion getOnePromotion(String promotionID) {
       Table table = dynamoDb.getTable("Promotion");
        try {
            Item item = table.getItem("idPromotion", promotionID);
            System.out.println("GetItem succeeded: " + item);
            return new Promotion(item.getString("idPromotion"), item.getString("nom"), item.getInt("montant"));
        } catch (Exception e) {
            System.err.println("Unable to get promotion: " + promotionID);
            System.err.println(e.getMessage());
            return null;
        }

    }

    public ArrayList<Promotion> getAllPromotion() {
        Table table = dynamoDb.getTable("Promotion");
        ArrayList<Promotion> promotions = new ArrayList<>();
        try {
            ItemCollection<ScanOutcome> items = table.scan();
            Iterator<Item> iterator = items.iterator();
            Item item = null;
            while (iterator.hasNext()) {
                item = iterator.next();
                promotions.add(new Promotion(item.getString("idPromotion"), item.getString("nom"), item.getInt("montant")));
            }
            return promotions;
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //Matériel

    public void insertMateriel(Materiel materiel) {
       Table table = dynamoDb.getTable("Materiel");
        try {
            Item item = new Item()
                    .withPrimaryKey("idMateriel", materiel.getId())
                    .withString("nom", materiel.getNom())
                    .withInt("prix", materiel.getPrix())
                    .withInt("caution", materiel.getCaution());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to add materiel: " + materiel.getNom());
            System.err.println(e.getMessage());
        }

    }

    public void deleteMateriel(Materiel materiel) {
        Table table = dynamoDb.getTable("Materiel");
        try {
            table.deleteItem("idMateriel", materiel.getId());
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete materiel: " + materiel.getNom());
            System.err.println(e.getMessage());
        }
    }

    public void updateMateriel(Materiel materiel) {
       Table table = dynamoDb.getTable("Materiel");
        try {
            Item item = new Item()
                    .withPrimaryKey("idMateriel", materiel.getId())
                    .withString("nom", materiel.getNom())
                    .withInt("prix", materiel.getPrix())
                    .withInt("caution", materiel.getCaution());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to update materiel: " + materiel.getNom());
            System.err.println(e.getMessage());
        }
    }

    public Materiel getOneMateriel(String materielID) {
       Table table = dynamoDb.getTable("Materiel");
        try {
            Item item = table.getItem("idMateriel", materielID);
            System.out.println("GetItem succeeded: " + item);
            return new Materiel(item.getString("idMateriel"), item.getString("nom"), item.getInt("prix"), item.getInt("caution"));
        } catch (Exception e) {
            System.err.println("Unable to get materiel: " + materielID);
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Materiel> getAllMateriel() {
      Table table = dynamoDb.getTable("Materiel");
        ArrayList<Materiel> materiels = new ArrayList<>();
        try {
            ItemCollection<ScanOutcome> items = table.scan();
            Iterator<Item> iterator = items.iterator();
            Item item = null;
            while (iterator.hasNext()) {
                item = iterator.next();
                materiels.add(new Materiel(item.getString("idMateriel"), item.getString("nom"), item.getInt("prix"), item.getInt("caution")));
            }
            return materiels;
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //Location
    public void insertLocation(Location location) {
       Table table = dynamoDb.getTable("Location");
        try {
            Item item = new Item()
                    .withPrimaryKey("idLocation", location.getId())
                    .withString("dateDebut", String.valueOf(location.getDateDebut()))
                    .withString("dateFin", String.valueOf(location.getDateFin()))
                    .withString("idClient", location.getClient().getId())
                    .withString("idPromotion", location.getPromotion().getId())
                    .withString("idBateau", location.getBateau().getIdBateau())
                    .withString("idSkipper", location.getSkipper().getId())
                    .withInt("prix", location.getPrix())
                    .withInt("forfait", location.getForfait())
                    .withMap("materiel", location.getMateriels());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        } catch (Exception e) {
            System.err.println("Unable to add location: " + location.getId());
            System.err.println(e.getMessage());
        }
    }

    public void deleteLocation(Location location) {
         Table table = dynamoDb.getTable("Location");
          try {
                table.deleteItem("idLocation", location.getId());
                System.out.println("DeleteItem succeeded");
          } catch (Exception e) {
                System.err.println("Unable to delete location: " + location.getId());
                System.err.println(e.getMessage());
          }
    }

    public void updateLocation(Location location) {
       Table table = dynamoDb.getTable("Location");
        try {
            Item item = new Item()
                    .withPrimaryKey("idLocation", location.getId())
                    .withString("dateDebut", String.valueOf(location.getDateDebut()))
                    .withString("dateFin", String.valueOf(location.getDateFin()))
                    .withString("idClient", location.getClient().getId())
                    .withString("idPromotion", location.getPromotion().getId())
                    .withString("idBateau", location.getBateau().getIdBateau())
                    .withString("idSkipper", location.getSkipper().getId())
                    .withInt("prix", location.getPrix())
                    .withInt("forfait", location.getForfait())
                    .withMap("materiel", location.getMateriels());
            PutItemOutcome outcome = table.putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
        } catch (Exception e) {
            System.err.println("Unable to update location: " + location.getId());
            System.err.println(e.getMessage());
        }
    }

    public Location getOneLocation(String locationID) {
        Table table = dynamoDb.getTable("Location");
        try {
            Item item = table.getItem("idLocation", locationID);
            System.out.println("GetItem succeeded: " + item);
            Date dateDebut = new SimpleDateFormat("dd/MM/yyyy").parse(item.getString("dateDebut"));
            Date dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(item.getString("dateFin"));
            HashMap<String, Integer> materiels = new HashMap<>();
            for (Map.Entry<String, Object> entry : item.getMap("materiel").entrySet()) {
                materiels.put(entry.getKey(), Integer.parseInt(entry.getValue().toString()));
            }
            return new Location(item.getString("idLocation"), item.getInt("nbPersonne"), dateDebut, dateFin, item.getInt("forfait"), item.getInt("prix"), getOnePromotion(item.getString("idPromotion")), getOneSkipper(item.getString("idSkipper")), getOneBateau(item.getString("idBateau")), getOneClient(item.getString("idClient")),  materiels);
        } catch (Exception e) {
            System.err.println("Unable to get location: " + locationID);
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Location> getAllLocation() {
       Table table = dynamoDb.getTable("Location");
        ArrayList<Location> locations = new ArrayList<>();
        try {
            ItemCollection<ScanOutcome> items = table.scan();
            Iterator<Item> iterator = items.iterator();
            Item item = null;
            while (iterator.hasNext()) {
                item = iterator.next();
                Date dateDebut = new SimpleDateFormat("dd/MM/yyyy").parse(item.getString("dateDebut"));
                Date dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(item.getString("dateFin"));
                HashMap<String, Integer> materiels = new HashMap<>();
                for (Map.Entry<String, Object> entry : item.getMap("materiel").entrySet()) {
                    materiels.put(entry.getKey(), Integer.parseInt(entry.getValue().toString()));
                }
                locations.add(new Location(item.getString("idLocation"), item.getInt("nbPersonne"), dateDebut, dateFin, item.getInt("forfait"), item.getInt("prix"), getOnePromotion(item.getString("idPromotion")), getOneSkipper(item.getString("idSkipper")), getOneBateau(item.getString("idBateau")), getOneClient(item.getString("idClient")),  materiels));
            }
            return locations;
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void afficherBateauxDispo() {
        ArrayList<Bateau> bateaux = getAllBateau();
        ArrayList<Location> locations = getAllLocation();
        for (Bateau bateau : bateaux) {
            boolean dispo = true;
            for (Location location : locations) {
                if (location.getBateau().equals(bateau)) {
                    dispo = false;
                }
            }
            if (dispo) {
                System.out.println(bateau);
            }
        }

    }


    public void ajouterBateau() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Code du bateau : ");
        String idBateau = sc.nextLine();
        System.out.println("Nombre de personne : ");
        int nbPersonne = sc.nextInt();
        System.out.println("Prix : ");
        int prix = sc.nextInt();
        System.out.println("Caution : ");
        int caution = sc.nextInt();
        int index = -1;
        do {
            System.out.println("Type du bateau : ");
            String typeBateau = sc.nextLine();
            index= getAllTypeBateau().indexOf(typeBateau);
        } while (index == -1);
        TypeBateau typeBateau = getAllTypeBateau().get(index);
        Bateau bateau = new Bateau(idBateau, nbPersonne, prix, caution, typeBateau.idTypeBateau, typeBateau.nomTypeBateau);
        insertBateau(bateau);

        System.out.println("Bateau ajouté");
    }

    public void ajouterSkipper() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Code: ");
        String idSkipper = sc.nextLine();
        System.out.println("Nom: ");
        String nbPersonne = sc.nextLine();
        System.out.println("Prenom : ");
        String prix = sc.nextLine();
        System.out.println("Tarif : ");
        int caution = sc.nextInt();
        Skipper skipper = new Skipper(idSkipper, nbPersonne, prix, caution);
        insertSkipper(skipper);
        System.out.println("Skipper ajouté");
    }

    public void ajouterClient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Code: ");
        String idClient = sc.nextLine();
        System.out.println("Nom: ");
        String nom = sc.nextLine();
        System.out.println("Prenom : ");
        String prenom = sc.nextLine();
        System.out.println("Adresse : ");
        String adresse = sc.nextLine();
        System.out.println("Code postal : ");
        String codePostal = sc.nextLine();
        System.out.println("Ville : ");
        String ville = sc.nextLine();
        System.out.println("Complement : ");
        String complement = sc.nextLine();
        System.out.println("Telephone : ");
        String telephone = sc.nextLine();
        System.out.println("Email : ");
        String email = sc.nextLine();
        System.out.println("Mot de passe : ");
        String motDePasse = sc.nextLine();
        Client client = new Client(idClient, nom, prenom, adresse, codePostal, ville, complement, telephone, email, motDePasse);
        insertClient(client);
        System.out.println("Client ajouté");

    }

    public void ajouterMateriel() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Code: ");
        String idMat = sc.nextLine();
        System.out.println("Nom: ");
        String nom = sc.nextLine();
        System.out.println("Prix : ");
        int prix = sc.nextInt();
        System.out.println("Caution : ");
        int caution = sc.nextInt();
        Materiel materiel = new Materiel(idMat, nom, prix, caution);
        insertMateriel(materiel);
        System.out.println("Materiel ajouté");
    }

    public void ajouterPromotion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Identifiant: ");
        String idPromo = sc.nextLine();

        System.out.println("Code: ");
        String code = sc.nextLine();
        System.out.println("Montant : ");
        int montant = sc.nextInt();
        Promotion promotion = new Promotion(idPromo, code, montant);
        insertPromotion(promotion);
        System.out.println("Promotion ajouté");
    }

    public void ajouterLocation() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Code: ");
        String idLoc = sc.nextLine();
        System.out.println("Nombre de personne : ");
        int nbPersonne = sc.nextInt();
        Date dateDebut = null;
        Date dateFin = null;

        do {
            System.out.println("Date de debut (dd/MM/yyyy): ");
            String dateDebutString = sc.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dateDebut = formatter.parse(dateDebutString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }while (dateDebut == null);

        do {
            System.out.println("Date de debut (dd/MM/yyyy): ");
            String dateFinString = sc.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dateFin = formatter.parse(dateFinString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }while (dateFin == null);
        System.out.println("Forfait : ");
        int forfait = sc.nextInt();
        System.out.println("Prix : ");
        int prix = sc.nextInt();
        Promotion promotion = null;
        String codePromotion = null;
        do {
            System.out.println("Promotion (indiquer l'id): ");
            codePromotion= sc.nextLine();
            String idPromotion = new String(codePromotion);
            promotion = getOnePromotion(idPromotion);
            if(promotion == null && !codePromotion.equals("")){
                System.out.println("Erreur : la promotion n'existe pas");
            }
        }while (promotion == null && !codePromotion.equals(""));
        Skipper skipper = null;
        do {
            System.out.println("Skipper (indiquer l'id): ");
            String nomSkipper = sc.nextLine();
            String idSkipper = new String(nomSkipper);
            skipper = getOneSkipper(idSkipper);
            if(skipper == null){
                System.out.println("Erreur : le skipper n'existe pas");
            }
        }while (skipper == null);
        Bateau bateau = null;
        do {
            System.out.println("Bateau (indiquer l'id): ");
            String nomBateau = sc.nextLine();
            String idBateau = new String(nomBateau);
            bateau = getOneBateau(idBateau);
            if(bateau == null){
                System.out.println("Erreur : le bateau n'existe pas");
            }
        }while (bateau == null);
        Client client = null;
        do {
            System.out.println("Client (indiquer l'id): ");
            String emailClient = sc.nextLine();
            String idClient = new String(emailClient);
            client = getOneClient(idClient);
            if(client == null){
                System.out.println("Erreur : le client n'existe pas");
            }
        }while (client == null);
        String codeMateriels = null;
        HashMap<String, Integer> listMateriel = new HashMap<String, Integer>();
        do {
            System.out.println("Materiel (indiquer les ids séparé par ',') : ");
            System.out.println("Quantite  (indiquer les ids séparé par ',') : ");

            codeMateriels= sc.nextLine();
            ArrayList<String> listCodeMateriel = new ArrayList<String>(Arrays.asList(codeMateriels.split(",")));
            ArrayList<String> listQuantite = new ArrayList<String>(Arrays.asList(codeMateriels.split(",")));
            if(listCodeMateriel.size() != listQuantite.size()){
                System.out.println("Erreur : le nombre de materiel et de quantite ne correspond pas");
            } else {
                for (int i = 0; i < listCodeMateriel.size(); i++) {
                    Materiel materiel = null;
                    String idMateriel = new String(listCodeMateriel.get(i));
                    materiel = getOneMateriel(idMateriel);
                    if (materiel == null) {
                        System.out.println("Erreur : le materiel: '" + listCodeMateriel.get(i) + "' n'existe pas");
                        break;
                    }
                    listMateriel.put(materiel.id, Integer.parseInt(listQuantite.get(i)));
                }
            }

        }while (listMateriel.size() == 0 && !codeMateriels.equals(""));

        Location location = new Location(idLoc, nbPersonne, dateDebut, dateFin, forfait, prix, promotion, skipper, bateau, client, listMateriel);
        insertLocation(location);
        System.out.println("Location ajouté");

    }

    public void supprimerBateau() {
        Scanner sc = new Scanner(System.in);
        Bateau bateau = null;

        do {
            System.out.println("Id du bateau à supprimer : ");
            String id = sc.nextLine();
            bateau = getOneBateau(new String(id));
            if(bateau == null){
                System.out.println("Erreur : le bateau n'existe pas");
                return;
            }
        }while (bateau == null);

        deleteBateau(bateau);
        System.out.println("Bateau supprimé");
    }

    public void supprimerSkipper() {
        Scanner sc = new Scanner(System.in);
        Skipper skipper = null;
        do {
             System.out.println("Id du skipper à supprimer : ");
            String id = sc.nextLine();
            skipper = getOneSkipper(new String(id));
            if(skipper == null){
                System.out.println("Erreur : le skipper n'existe pas");
                return;
            }
        }while (skipper == null);
        deleteSkipper(skipper);
        System.out.println("Skipper supprimé");
    }

    public void supprimerClient() {
        Scanner sc = new Scanner(System.in);
        Client client = null;
        do {
            System.out.println("Id du client à supprimer : ");
            String id = sc.nextLine();
            client = getOneClient(new String(id));
            if(client == null){
                System.out.println("Erreur : le client n'existe pas");
                return;
            }
        }while (client == null);
        deleteClient(client);
        System.out.println("Client supprimé");
    }

    public void supprimerMateriel() {
        Scanner sc = new Scanner(System.in);
        Materiel materiel = null;
        do {
            System.out.println("Id du materiel à supprimer : ");
            String id = sc.nextLine();
            materiel = getOneMateriel(new String(id));
            if(materiel == null){
                System.out.println("Erreur : le materiel n'existe pas");
                return;
            }
        }while (materiel == null);
        deleteMateriel(materiel);
        System.out.println("Materiel supprimé");
    }

    public void supprimerPromotion() {
        Scanner sc = new Scanner(System.in);
        Promotion promotion = null;
        do {
            System.out.println("Id de la promotion à supprimer : ");
            String id = sc.nextLine();
            promotion = getOnePromotion(new String(id));
            if(promotion == null){
                System.out.println("Erreur : la promotion n'existe pas");
                return;
            }
        }while (promotion == null);
        deletePromotion(promotion);
        System.out.println("Promotion supprimé");
    }

    public void supprimerLocation() {
        Scanner sc = new Scanner(System.in);
        Location location = null;
        do {
            System.out.println("Id de la location à supprimer : ");
            String id = sc.nextLine();
            location = getOneLocation(new String(id));
            if(location == null){
                System.out.println("Erreur : la location n'existe pas");
                return;
            }
        }while (location == null);
        deleteLocation(location);
        System.out.println("Location supprimé");
    }

    public void modifierBateau() {
        Scanner sc = new Scanner(System.in);
        Bateau bateau = null;
        do {
            System.out.println("Id du bateau à modifier : ");
            String id = sc.nextLine();
            bateau = getOneBateau(new String(id));
            if(bateau == null){
                System.out.println("Erreur : le bateau n'existe pas");
                return;
            }
        }while (bateau == null);

        System.out.println("Nombre de personne ("+bateau.getNbPlace()+") : ");
        int nbPersonne = sc.nextInt();
        System.out.println("Puissance ("+bateau.getPuissance()+"): ");
        int puisssance = sc.nextInt();
        System.out.println("Caution ("+bateau.getCaution()+"): ");
        int caution = sc.nextInt();
        int index = -1;
        do {
            System.out.println("Type du bateau ("+bateau.getIdTypeBateau()+"): ");
            String typeBateau = sc.nextLine();
            index= getAllTypeBateau().indexOf(typeBateau);
        } while (index == -1);
        TypeBateau typeBateau = getAllTypeBateau().get(index);
        bateau.setNbPersonne(nbPersonne);
        bateau.setPuissance(puisssance);
        bateau.setCaution(caution);
        bateau.setTypeBateau(typeBateau);
        updateBateau(bateau);
        System.out.println("Bateau mis à jour");

    }

    public void modifierSkipper() {
        Scanner sc = new Scanner(System.in);
        Skipper skipper = null;
        do {
            System.out.println("Id du skipper à modifier : ");
            String id = sc.nextLine();
            skipper = getOneSkipper(new String(id));
            if(skipper == null){
                System.out.println("Erreur : le skipper n'existe pas");
                return;
            }
        }while (skipper == null);

        System.out.println("Nom ("+skipper.getNom()+") : ");
        String nom = sc.nextLine();
        System.out.println("Prenom ("+skipper.getPrenom()+"): ");
        String prenom = sc.nextLine();
        System.out.println("Tarif("+skipper.getTarif()+"): ");
        int prix = sc.nextInt();
        skipper.setNom(nom);
        skipper.setPrenom(prenom);
        skipper.setTarif(prix);
        updateSkipper(skipper);
        System.out.println("Skipper mis à jour");

    }

    public void modifierClient() {

        Scanner sc = new Scanner(System.in);
        Client client = null;
        do {
            System.out.println("Id du client à modifier : ");
            String id = sc.nextLine();
            client = getOneClient(new String(id));
            if(client == null){
                System.out.println("Erreur : le client n'existe pas");
                return;
            }
        }while (client == null);

        System.out.println("Nom ("+client.getNom()+") : ");
        String nom = sc.nextLine();
        System.out.println("Prenom ("+client.getPrenom()+"): ");
        String prenom = sc.nextLine();
        System.out.println("Adresse ("+client.getAdresse()+"): ");
        String adresse = sc.nextLine();
        System.out.println("Telephone ("+client.getTelephone()+"): ");
        String telephone = sc.nextLine();
        System.out.println("Email ("+client.getEmail()+"): ");
        String email = sc.nextLine();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(adresse);
        client.setTelephone(telephone);
        client.setEmail(email);
        updateClient(client);
        System.out.println("Client mis à jour");

    }

    public void modifierMateriel() {
        Scanner sc = new Scanner(System.in);
        Materiel materiel = null;
        do {
            System.out.println("Id du materiel à modifier : ");
            String id = sc.nextLine();
            materiel = getOneMateriel(new String(id));
            if(materiel == null){
                System.out.println("Erreur : le materiel n'existe pas");
                return;
            }
        }while (materiel == null);

        System.out.println("Nom ("+materiel.getNom()+") : ");
        String nom = sc.nextLine();
        System.out.println("Prix ("+materiel.getPrix()+"): ");
        int prix = sc.nextInt();
        System.out.println("Caution ("+materiel.getCaution()+"): ");
        int caution = sc.nextInt();
        materiel.setNom(nom);
        materiel.setPrix(prix);
        materiel.setCaution(caution);
        updateMateriel(materiel);
        System.out.println("Materiel mis à jour");

    }

    public void modifierPromotion() {
        Scanner sc = new Scanner(System.in);
        Promotion promotion = null;
        do {
            System.out.println("Id de la promotion à modifier : ");
            String id = sc.nextLine();
            promotion = getOnePromotion(new String(id));
            if(promotion == null){
                System.out.println("Erreur : la promotion n'existe pas");
                return;
            }
        }while (promotion == null);

        System.out.println("Code ("+promotion.getCode()+") : ");
        String code = sc.nextLine();
        System.out.println("Montant ("+promotion.getMontant()+"): ");
        int pourcentage = sc.nextInt();
        promotion.setCode(code);
        promotion.setMontant(pourcentage);
        updatePromotion(promotion);
        System.out.println("Promotion mis à jour");

    }

    public void afficherBateaux() {
        List<Bateau> bateaux = getAllBateau();
        for (Bateau bateau : bateaux) {
            System.out.println(bateau);
        }
    }

    public void afficherSkippers() {
        List<Skipper> skippers = getAllSkipper();
        for (Skipper skipper : skippers) {
            System.out.println(skipper);
        }
    }

    public void afficherClients() {
        List<Client> clients = getAllClient();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public void afficherMateriels() {
        List<Materiel> materiels = getAllMateriel();
        for (Materiel materiel : materiels) {
            System.out.println(materiel);
        }
    }

    public void afficherPromotion() {
        List<Promotion> promotions = getAllPromotion();
        for (Promotion promotion : promotions) {
            System.out.println(promotion);
        }
    }

    public void affficherLocation() {
        List<Location> locations = getAllLocation();
        for (Location location : locations) {
            System.out.println(location);
        }
    }
}
