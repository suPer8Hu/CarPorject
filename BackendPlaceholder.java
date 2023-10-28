import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BackendPlaceholder implements BackendInterface {
  private RedBlackTree<Car> tree = new RedBlackTree<Car>();

  @Override
  public int readDataFile(String fileName) {
    tree.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line = br.readLine();
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        tree.insert(new Car(Double.valueOf(values[1]), values[2], values[3],
            Integer.valueOf(values[4]), Double.valueOf(values[6])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return tree.size;
  }

  @Override
  public List<Car> carWithMinMileage(double minMileage) {
    List<Car> res = new ArrayList<>();
    BinarySearchTree.Node<Car> curNode = tree.root;
    carWithMinMileageHelper(minMileage, res, curNode);
    return res;
  }

  private void carWithMinMileageHelper(double minMileage, List<Car> res,
      BinarySearchTree.Node<Car> cur) {
    if (cur == null) {
      return;
    }

    carWithMinMileageHelper(minMileage, res, cur.down[0]);
    if (cur.data.Mileage() <= minMileage) {
      res.add(cur.data);
    }
    carWithMinMileageHelper(minMileage, res, cur.down[1]);
  }

  @Override
  public List<Car> carsAtThreshold(double threshold) {
    List<Car> res = new ArrayList<>();
    BinarySearchTree.Node<Car> curNode = tree.root;
    carsAtThresholdHelper(threshold, res, curNode);
    return res;
  }

  private void carsAtThresholdHelper(double threshold, List<Car> res,
      BinarySearchTree.Node<Car> cur) {
    if (cur == null) {
      return;
    }

    carsAtThresholdHelper(threshold, res, cur.down[0]);
    if (cur.data.Mileage() >= threshold) {
      res.add(cur.data);
    }
    carsAtThresholdHelper(threshold, res, cur.down[1]);
  }

  public static void main(String[] args) {
    BackendInterface backend = new BackendPlaceholder();
    FrontendInterface frontend = new FrontendPlaceholder(backend);

    frontend.run();
  }

}


