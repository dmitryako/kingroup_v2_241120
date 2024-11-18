package tomsk.io.pdb;
import junit.framework.TestCase;
import tomsk.domain.particle.Particle;
import tomsk.domain.particle.visitor.ParticleVisitor;
import tomsk.project.Tomsk;
import tomsk.project.TomskProject;
import tomsk.ucm.utils.UCShow3dView;
import tomsk.view.j3dx.PDB3dView;
import tomsk.view.theme.View3dFactoryParticleVisitor;
import tomsk.view.tomsk3d.Tomsk3dModel;
import tsvlib.project.ProjectLogger;

import javax.iox.TableReader;
import javax.iox.TextFile;
import javax.swing.*;

/**
 * Copyright: www.DmitryKonovalov.org, jc138691, 8/05/2007, 17:13:07
 */
public class PDBReaderTest extends TestCase
{
  private static ProjectLogger log = ProjectLogger.getLogger(PDBReaderTest.class);

  public static void main( String[] args )
  {
    JPopupMenu.setDefaultLightWeightPopupEnabled( false );
    ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
//    new PDBReaderTest().testRead();
    new PDBReaderTest().test2();
  }

  public void testRead() {
    //CC(=O)C.pdb
    String text = "COMPND    3969193\n" +
      "HETATM    1  C1  LIG     1      -0.018   1.503   0.010  1.00  0.00\n" +
      "HETATM    2  C2  LIG     1       0.002  -0.004   0.002  1.00  0.00\n" +
      "HETATM    3  O3  LIG     1       0.020  -0.613   1.044  1.00  0.00\n" +
      "HETATM    4  C4  LIG     1      -0.000  -0.751  -1.307  1.00  0.00\n" +
      "HETATM    5 1H1  LIG     1       1.004   1.880   0.003  1.00  0.00\n" +
      "HETATM    6 2H1  LIG     1      -0.545   1.864  -0.873  1.00  0.00\n" +
      "HETATM    7 3H1  LIG     1      -0.529   1.854   0.907  1.00  0.00\n" +
      "HETATM    8 1H4  LIG     1      -0.018  -0.038  -2.132  1.00  0.00\n" +
      "HETATM    9 2H4  LIG     1       0.898  -1.365  -1.377  1.00  0.00\n" +
      "HETATM   10 3H4  LIG     1      -0.882  -1.389  -1.360  1.00  0.00\n" +
      "END";

    log.start("PDBReaderTest");

    TextFile from = new TextFile();
    from.addLines(text);

    ProjectLogger.getRootLogger().setAll();
    ProjectLogger.getLogger(TableReader.class).setInfo();
    ProjectLogger.getLogger(View3dFactoryParticleVisitor.class).setInfo();
    ProjectLogger.getLogger(ParticleVisitor.class).setInfo();
    ProjectLogger.getLogger(Particle.class).setInfo();

    PDBModel model = PDBReader.makeFromFile(from, true);

    Tomsk project = TomskProject.makeInstance("PDBReaderTest", "070515");
    Tomsk3dModel tomsk3d = project.getTomsk3dModel();
    PDB3dView view = new PDB3dView(model, tomsk3d);

//    new UCShow3dView(new Tomsk3dUI(null), "Tomsk3dUI").run();
    new UCShow3dView(view, "PDB3dView").run();
    int dbg = 0;
  }

  public void test2() {
    log.start("PDBReaderTest");
    TextFile from = new TextFile();
    from.addLines(PDB_TEST_FILE);

    ProjectLogger.getRootLogger().setAll();
    ProjectLogger.getLogger(TableReader.class).setInfo();
    ProjectLogger.getLogger("tsvlib.project").setInfo();
//    ProjectLogger.getLogger(View3dFactoryParticleVisitor.class).setInfo();
//    ProjectLogger.getLogger(ParticleVisitor.class).setInfo();
//    ProjectLogger.getLogger(Particle.class).setInfo();    

    PDBModel model = PDBReader.makeFromFile(from, true);

    Tomsk project = TomskProject.makeInstance("PDBReaderTest", "070520");
    Tomsk3dModel tomsk3d = project.getTomsk3dModel();
    PDB3dView view = new PDB3dView(model, tomsk3d);

//    new UCShow3dView(new Tomsk3dUI(null), "Tomsk3dUI").run();
    new UCShow3dView(view, "PDB3dView").run();
    int dbg = 0;
  }

  public String PDB_TEST_FILE =
    "HEADER    PLANT SEED PROTEIN                      30-APR-81   1CRN      1CRND  1\n" +
      "COMPND    CRAMBIN                                                       1CRN   4\n" +
      "SOURCE    ABYSSINIAN CABBAGE (CRAMBE ABYSSINICA) SEED                   1CRN   5\n" +
      "AUTHOR    W.A.HENDRICKSON,M.M.TEETER                                    1CRN   6\n" +
      "REVDAT   5   16-APR-87 1CRND   1       HEADER                           1CRND  2\n" +
      "REVDAT   4   04-MAR-85 1CRNC   1       REMARK                           1CRNC  1\n" +
      "REVDAT   3   30-SEP-83 1CRNB   1       REVDAT                           1CRNB  1\n" +
      "REVDAT   2   03-DEC-81 1CRNA   1       SHEET                            1CRNB  2\n" +
      "REVDAT   1   28-JUL-81 1CRN    0                                        1CRNB  3\n" +
      "REMARK   1                                                              1CRN   7\n" +
      "REMARK   1 REFERENCE 1                                                  1CRNC  2\n" +
      "REMARK   1  AUTH   M.M.TEETER                                           1CRNC  3\n" +
      "REMARK   1  TITL   WATER STRUCTURE OF A HYDROPHOBIC PROTEIN AT ATOMIC   1CRNC  4\n" +
      "REMARK   1  TITL 2 RESOLUTION. PENTAGON RINGS OF WATER MOLECULES IN     1CRNC  5\n" +
      "REMARK   1  TITL 3 CRYSTALS OF CRAMBIN                                  1CRNC  6\n" +
      "REMARK   1  REF    PROC.NAT.ACAD.SCI.USA         V.  81  6014 1984      1CRNC  7\n" +
      "REMARK   1  REFN   ASTM PNASA6  US ISSN 0027-8424                  040  1CRNC  8\n" +
      "REMARK   1 REFERENCE 2                                                  1CRNC  9\n" +
      "REMARK   1  AUTH   W.A.HENDRICKSON,M.M.TEETER                           1CRN   9\n" +
      "REMARK   1  TITL   STRUCTURE OF THE HYDROPHOBIC PROTEIN CRAMBIN         1CRN  10\n" +
      "REMARK   1  TITL 2 DETERMINED DIRECTLY FROM THE ANOMALOUS SCATTERING    1CRN  11\n" +
      "REMARK   1  TITL 3 OF SULPHUR                                           1CRN  12\n" +
      "REMARK   1  REF    NATURE                        V.  290  107 1981      1CRN  13\n" +
      "REMARK   1  REFN   ASTM NATUAS  UK ISSN 0028-0836                  006  1CRN  14\n" +
      "REMARK   1 REFERENCE 3                                                  1CRNC 10\n" +
      "REMARK   1  AUTH   M.M.TEETER,W.A.HENDRICKSON                           1CRN  16\n" +
      "REMARK   1  TITL   HIGHLY ORDERED CRYSTALS OF THE PLANT SEED PROTEIN    1CRN  17\n" +
      "REMARK   1  TITL 2 CRAMBIN                                              1CRN  18\n" +
      "REMARK   1  REF    J.MOL.BIOL.                   V.  127  219 1979      1CRN  19\n" +
      "REMARK   1  REFN   ASTM JMOBAK  UK ISSN 0022-2836                  070  1CRN  20\n" +
      "REMARK   2                                                              1CRN  21\n" +
      "REMARK   2 RESOLUTION. 1.5 ANGSTROMS.                                   1CRN  22\n" +
      "REMARK   3                                                              1CRN  23\n" +
      "REMARK   3 REFINEMENT. RESTRAINED LEAST SQUARES (HENDRICKSON,W.A.,      1CRN  24\n" +
      "REMARK   3  KONNERT,J.H. COMPUTING IN CRYSTALLOGRAPHY, EDS.DIAMOND,R.,  1CRN  25\n" +
      "REMARK   3  RAMASESHAN,S.,VENKATESAN,K. (1980)).                        1CRN  26\n" +
      "REMARK   4                                                              1CRN  27\n" +
      "REMARK   4 CONFORMATIONAL HETEROGENEITY EXISTS AT ILE 7 AND ILE 25      1CRN  28\n" +
      "REMARK   4 WHERE CD1 ATOMS TAKE EITHER OF TWO STAGGERED POSSIBILITIES.  1CRN  29\n" +
      "REMARK   4 COMPOSITIONAL HETEROGENEITY ALSO EXISTS AT POSITIONS 22 AND  1CRN  30\n" +
      "REMARK   4 25.  REFINEMENT PARAMETERS SUGGEST THAT RESIDUE 22 IS ABOUT  1CRN  31\n" +
      "REMARK   4 60/40 PRO/SER AND THAT RESIDUE 25 IS ABOUT 60/40 ILE/LEU.    1CRN  32\n" +
      "REMARK   4 THE HETEROGENEITY AT RESIDUE 22 APPARENTLY CAUSES A          1CRN  33\n" +
      "REMARK   4 DISORDER IN TYR 29 - THE REFINED POSITION OF ITS OH ATOM     1CRN  34\n" +
      "REMARK   4 MAKES AN IMPOSSIBLY SHORT CONTACT OF 2.6 ANGSTROMS WITH      1CRN  35\n" +
      "REMARK   4 ATOM CD OF PRO 22 ON A SCREW-RELATED MOLECULE.  THE          1CRN  36\n" +
      "REMARK   4 DEPOSITED COORDINATES ARE ONLY FOR THE MAJOR CONTRIBUTOR AT  1CRN  37\n" +
      "REMARK   4 EACH SITE (PRO 22 AND ILE 25).  DEPOSITION OF THE MODEL OF   1CRN  38\n" +
      "REMARK   4 DISORDER AND SOLVENT STRUCTURE IS DEFERRED UNTIL HIGHER      1CRN  39\n" +
      "REMARK   4 RESOLUTION REFINEMENT.  THE R-FACTOR FOR THE COMPLETE MODEL  1CRN  40\n" +
      "REMARK   4 INCLUDING HETEROGENEITY AND SOLVENT IS 0.114 ISOTROPIC AND   1CRN  41\n" +
      "REMARK   4 0.104 ANISOTROPIC AGAINST ALL DATA IN THE 10.0 TO 1.5        1CRN  42\n" +
      "REMARK   4 ANGSTROM SHELL.                                              1CRN  43\n" +
      "REMARK   5                                                              1CRN  44\n" +
      "REMARK   5 THE SECONDARY STRUCTURE SPECIFICATIONS ARE THOSE DEFINED     1CRN  45\n" +
      "REMARK   5 IN REFERENCE 1 ABOVE AND DEPEND ON PARTICULAR DEFINITIONS    1CRN  46\n" +
      "REMARK   5 THAT MAY AFFECT THE DETERMINATION OF END POINTS.  PLEASE     1CRN  47\n" +
      "REMARK   5 CONSULT THE PRIMARY REFERENCE AND EXAMINE STRUCTURAL         1CRN  48\n" +
      "REMARK   5 DETAILS SUCH AS HYDROGEN BONDING AND CONFORMATION ANGLES     1CRN  49\n" +
      "REMARK   5 WHEN MAKING USE OF THE SPECIFICATIONS.                       1CRN  50\n" +
      "REMARK   6                                                              1CRNA  1\n" +
      "REMARK   6 CORRECTION. CORRECT RESIDUE NUMBER ON STRAND 1 OF SHEET S1.  1CRNA  2\n" +
      "REMARK   6  03-DEC-81.                                                  1CRNA  3\n" +
      "REMARK   7                                                              1CRNB  4\n" +
      "REMARK   7 CORRECTION. INSERT REVDAT RECORDS. 30-SEP-83.                1CRNB  5\n" +
      "REMARK   8                                                              1CRNC 11\n" +
      "REMARK   8 CORRECTION. INSERT NEW PUBLICATION AS REFERENCE 1 AND        1CRNC 12\n" +
      "REMARK   8  RENUMBER THE OTHERS.  04-MAR-85.                            1CRNC 13\n" +
      "REMARK   9                                                              1CRND  3\n" +
      "REMARK   9 CORRECTION. CHANGE DEPOSITION DATE FROM 31-APR-81 TO         1CRND  4\n" +
      "REMARK   9  30-APR-81.  16-APR-87.                                      1CRND  5\n" +
      "SEQRES   1     46  THR THR CYS CYS PRO SER ILE VAL ALA ARG SER ASN PHE  1CRN  51\n" +
      "SEQRES   2     46  ASN VAL CYS ARG LEU PRO GLY THR PRO GLU ALA ILE CYS  1CRN  52\n" +
      "SEQRES   3     46  ALA THR TYR THR GLY CYS ILE ILE ILE PRO GLY ALA THR  1CRN  53\n" +
      "SEQRES   4     46  CYS PRO GLY ASP TYR ALA ASN                          1CRN  54\n" +
      "HELIX    1  H1 ILE      7  PRO     19  1 3/10 CONFORMATION RES 17,19    1CRN  55\n" +
      "HELIX    2  H2 GLU     23  THR     30  1 DISTORTED 3/10 AT RES 30       1CRN  56\n" +
      "SHEET    1  S1 2 THR     1  CYS     4  0                                1CRNA  4\n" +
      "SHEET    2  S1 2 CYS    32  ILE    35 -1                                1CRN  58\n" +
      "TURN     1  T1 PRO    41  TYR    44                                     1CRN  59\n" +
      "SSBOND   1 CYS      3    CYS     40                                     1CRN  60\n" +
      "SSBOND   2 CYS      4    CYS     32                                     1CRN  61\n" +
      "SSBOND   3 CYS     16    CYS     26                                     1CRN  62\n" +
      "CRYST1   40.960   18.650   22.520  90.00  90.77  90.00 P 21          2  1CRN  63\n" +
      "ORIGX1      1.000000  0.000000  0.000000        0.00000                 1CRN  64\n" +
      "ORIGX2      0.000000  1.000000  0.000000        0.00000                 1CRN  65\n" +
      "ORIGX3      0.000000  0.000000  1.000000        0.00000                 1CRN  66\n" +
      "SCALE1       .024414  0.000000  -.000328        0.00000                 1CRN  67\n" +
      "SCALE2      0.000000   .053619  0.000000        0.00000                 1CRN  68\n" +
      "SCALE3      0.000000  0.000000   .044409        0.00000                 1CRN  69\n" +
      "ATOM      1  N   THR     1      17.047  14.099   3.625  1.00 13.79      1CRN  70\n" +
      "ATOM      2  CA  THR     1      16.967  12.784   4.338  1.00 10.80      1CRN  71\n" +
      "ATOM      3  C   THR     1      15.685  12.755   5.133  1.00  9.19      1CRN  72\n" +
      "ATOM      4  O   THR     1      15.268  13.825   5.594  1.00  9.85      1CRN  73\n" +
      "ATOM      5  CB  THR     1      18.170  12.703   5.337  1.00 13.02      1CRN  74\n" +
      "ATOM      6  OG1 THR     1      19.334  12.829   4.463  1.00 15.06      1CRN  75\n" +
      "ATOM      7  CG2 THR     1      18.150  11.546   6.304  1.00 14.23      1CRN  76\n" +
      "ATOM      8  N   THR     2      15.115  11.555   5.265  1.00  7.81      1CRN  77\n" +
      "ATOM      9  CA  THR     2      13.856  11.469   6.066  1.00  8.31      1CRN  78\n" +
      "ATOM     10  C   THR     2      14.164  10.785   7.379  1.00  5.80      1CRN  79\n" +
      "ATOM     11  O   THR     2      14.993   9.862   7.443  1.00  6.94      1CRN  80\n" +
      "ATOM     12  CB  THR     2      12.732  10.711   5.261  1.00 10.32      1CRN  81\n" +
      "ATOM     13  OG1 THR     2      13.308   9.439   4.926  1.00 12.81      1CRN  82\n" +
      "ATOM     14  CG2 THR     2      12.484  11.442   3.895  1.00 11.90      1CRN  83\n" +
      "ATOM     15  N   CYS     3      13.488  11.241   8.417  1.00  5.24      1CRN  84\n" +
      "ATOM     16  CA  CYS     3      13.660  10.707   9.787  1.00  5.39      1CRN  85\n" +
      "ATOM     17  C   CYS     3      12.269  10.431  10.323  1.00  4.45      1CRN  86\n" +
      "ATOM     18  O   CYS     3      11.393  11.308  10.185  1.00  6.54      1CRN  87\n" +
      "ATOM     19  CB  CYS     3      14.368  11.748  10.691  1.00  5.99      1CRN  88\n" +
      "ATOM     20  SG  CYS     3      15.885  12.426  10.016  1.00  7.01      1CRN  89\n" +
      "ATOM     21  N   CYS     4      12.019   9.272  10.928  1.00  3.90      1CRN  90\n" +
      "ATOM     22  CA  CYS     4      10.646   8.991  11.408  1.00  4.24      1CRN  91\n" +
      "ATOM     23  C   CYS     4      10.654   8.793  12.919  1.00  3.72      1CRN  92\n" +
      "ATOM     24  O   CYS     4      11.659   8.296  13.491  1.00  5.30      1CRN  93\n" +
      "ATOM     25  CB  CYS     4      10.057   7.752  10.682  1.00  4.41      1CRN  94\n" +
      "ATOM     26  SG  CYS     4       9.837   8.018   8.904  1.00  4.72      1CRN  95\n" +
      "ATOM     27  N   PRO     5       9.561   9.108  13.563  1.00  3.96      1CRN  96\n" +
      "ATOM     28  CA  PRO     5       9.448   9.034  15.012  1.00  4.25      1CRN  97\n" +
      "ATOM     29  C   PRO     5       9.288   7.670  15.606  1.00  4.96      1CRN  98\n" +
      "ATOM     30  O   PRO     5       9.490   7.519  16.819  1.00  7.44      1CRN  99\n" +
      "ATOM     31  CB  PRO     5       8.230   9.957  15.345  1.00  5.11      1CRN 100\n" +
      "ATOM     32  CG  PRO     5       7.338   9.786  14.114  1.00  5.24      1CRN 101\n" +
      "ATOM     33  CD  PRO     5       8.366   9.804  12.958  1.00  5.20      1CRN 102\n" +
      "ATOM     34  N   SER     6       8.875   6.686  14.796  1.00  4.83      1CRN 103\n" +
      "ATOM     35  CA  SER     6       8.673   5.314  15.279  1.00  4.45      1CRN 104\n" +
      "ATOM     36  C   SER     6       8.753   4.376  14.083  1.00  4.99      1CRN 105\n" +
      "ATOM     37  O   SER     6       8.726   4.858  12.923  1.00  4.61      1CRN 106\n" +
      "ATOM     38  CB  SER     6       7.340   5.121  15.996  1.00  5.05      1CRN 107\n" +
      "ATOM     39  OG  SER     6       6.274   5.220  15.031  1.00  6.39      1CRN 108\n" +
      "ATOM     40  N   ILE     7       8.881   3.075  14.358  1.00  4.94      1CRN 109\n" +
      "ATOM     41  CA  ILE     7       8.912   2.083  13.258  1.00  6.33      1CRN 110\n" +
      "ATOM     42  C   ILE     7       7.581   2.090  12.506  1.00  5.32      1CRN 111\n" +
      "ATOM     43  O   ILE     7       7.670   2.031  11.245  1.00  6.85      1CRN 112\n" +
      "ATOM     44  CB  ILE     7       9.207    .677  13.924  1.00  8.43      1CRN 113\n" +
      "ATOM     45  CG1 ILE     7      10.714    .702  14.312  1.00  9.78      1CRN 114\n" +
      "ATOM     46  CG2 ILE     7       8.811   -.477  12.969  1.00 11.70      1CRN 115\n" +
      "ATOM     47  CD1 ILE     7      11.185   -.516  15.142  1.00  9.92      1CRN 116\n" +
      "ATOM     48  N   VAL     8       6.458   2.162  13.159  1.00  5.02      1CRN 117\n" +
      "ATOM     49  CA  VAL     8       5.145   2.209  12.453  1.00  6.93      1CRN 118\n" +
      "ATOM     50  C   VAL     8       5.115   3.379  11.461  1.00  5.39      1CRN 119\n" +
      "ATOM     51  O   VAL     8       4.664   3.268  10.343  1.00  6.30      1CRN 120\n" +
      "ATOM     52  CB  VAL     8       3.995   2.354  13.478  1.00  9.64      1CRN 121\n" +
      "ATOM     53  CG1 VAL     8       2.716   2.891  12.869  1.00 13.85      1CRN 122\n" +
      "ATOM     54  CG2 VAL     8       3.758   1.032  14.208  1.00 11.97      1CRN 123\n" +
      "ATOM     55  N   ALA     9       5.606   4.546  11.941  1.00  3.73      1CRN 124\n" +
      "ATOM     56  CA  ALA     9       5.598   5.767  11.082  1.00  3.56      1CRN 125\n" +
      "ATOM     57  C   ALA     9       6.441   5.527   9.850  1.00  4.13      1CRN 126\n" +
      "ATOM     58  O   ALA     9       6.052   5.933   8.744  1.00  4.36      1CRN 127\n" +
      "ATOM     59  CB  ALA     9       6.022   6.977  11.891  1.00  4.80      1CRN 128\n" +
      "ATOM     60  N   ARG    10       7.647   4.909  10.005  1.00  3.73      1CRN 129\n" +
      "ATOM     61  CA  ARG    10       8.496   4.609   8.837  1.00  3.38      1CRN 130\n" +
      "ATOM     62  C   ARG    10       7.798   3.609   7.876  1.00  3.47      1CRN 131\n" +
      "ATOM     63  O   ARG    10       7.878   3.778   6.651  1.00  4.67      1CRN 132\n" +
      "ATOM     64  CB  ARG    10       9.847   4.020   9.305  1.00  3.95      1CRN 133\n" +
      "ATOM     65  CG  ARG    10      10.752   3.607   8.149  1.00  4.55      1CRN 134\n" +
      "ATOM     66  CD  ARG    10      11.226   4.699   7.244  1.00  5.89      1CRN 135\n" +
      "ATOM     67  NE  ARG    10      12.143   5.571   8.035  1.00  6.20      1CRN 136\n" +
      "ATOM     68  CZ  ARG    10      12.758   6.609   7.443  1.00  7.52      1CRN 137\n" +
      "ATOM     69  NH1 ARG    10      12.539   6.932   6.158  1.00 10.68      1CRN 138\n" +
      "ATOM     70  NH2 ARG    10      13.601   7.322   8.202  1.00  9.48      1CRN 139\n" +
      "ATOM     71  N   SER    11       7.186   2.582   8.445  1.00  5.19      1CRN 140\n" +
      "ATOM     72  CA  SER    11       6.500   1.584   7.565  1.00  4.60      1CRN 141\n" +
      "ATOM     73  C   SER    11       5.382   2.313   6.773  1.00  4.84      1CRN 142\n" +
      "ATOM     74  O   SER    11       5.213   2.016   5.557  1.00  5.84      1CRN 143\n" +
      "ATOM     75  CB  SER    11       5.908    .462   8.400  1.00  5.91      1CRN 144\n" +
      "ATOM     76  OG  SER    11       6.990   -.272   9.012  1.00  8.38      1CRN 145\n" +
      "ATOM     77  N   ASN    12       4.648   3.182   7.446  1.00  3.54      1CRN 146\n" +
      "ATOM     78  CA  ASN    12       3.545   3.935   6.751  1.00  4.57      1CRN 147\n" +
      "ATOM     79  C   ASN    12       4.107   4.851   5.691  1.00  4.14      1CRN 148\n" +
      "ATOM     80  O   ASN    12       3.536   5.001   4.617  1.00  5.52      1CRN 149\n" +
      "ATOM     81  CB  ASN    12       2.663   4.677   7.748  1.00  6.42      1CRN 150\n" +
      "ATOM     82  CG  ASN    12       1.802   3.735   8.610  1.00  8.25      1CRN 151\n" +
      "ATOM     83  OD1 ASN    12       1.567   2.613   8.165  1.00 12.72      1CRN 152\n" +
      "ATOM     84  ND2 ASN    12       1.394   4.252   9.767  1.00  9.92      1CRN 153\n" +
      "ATOM     85  N   PHE    13       5.259   5.498   6.005  1.00  3.43      1CRN 154\n" +
      "ATOM     86  CA  PHE    13       5.929   6.358   5.055  1.00  3.49      1CRN 155\n" +
      "ATOM     87  C   PHE    13       6.304   5.578   3.799  1.00  3.40      1CRN 156\n" +
      "ATOM     88  O   PHE    13       6.136   6.072   2.653  1.00  4.07      1CRN 157\n" +
      "ATOM     89  CB  PHE    13       7.183   6.994   5.754  1.00  5.48      1CRN 158\n" +
      "ATOM     90  CG  PHE    13       7.884   8.006   4.883  1.00  5.57      1CRN 159\n" +
      "ATOM     91  CD1 PHE    13       8.906   7.586   4.027  1.00  6.99      1CRN 160\n" +
      "ATOM     92  CD2 PHE    13       7.532   9.373   4.983  1.00  6.52      1CRN 161\n" +
      "ATOM     93  CE1 PHE    13       9.560   8.539   3.194  1.00  8.20      1CRN 162\n" +
      "ATOM     94  CE2 PHE    13       8.176  10.281   4.145  1.00  6.34      1CRN 163\n" +
      "ATOM     95  CZ  PHE    13       9.141   9.845   3.292  1.00  6.84      1CRN 164\n" +
      "ATOM     96  N   ASN    14       6.900   4.390   3.989  1.00  3.64      1CRN 165\n" +
      "ATOM     97  CA  ASN    14       7.331   3.607   2.791  1.00  4.31      1CRN 166\n" +
      "ATOM     98  C   ASN    14       6.116   3.210   1.915  1.00  3.98      1CRN 167\n" +
      "ATOM     99  O   ASN    14       6.240   3.144    .684  1.00  6.22      1CRN 168\n" +
      "ATOM    100  CB  ASN    14       8.145   2.404   3.240  1.00  5.81      1CRN 169\n" +
      "ATOM    101  CG  ASN    14       9.555   2.856   3.730  1.00  6.82      1CRN 170\n" +
      "ATOM    102  OD1 ASN    14      10.013   3.895   3.323  1.00  9.43      1CRN 171\n" +
      "ATOM    103  ND2 ASN    14      10.120   1.956   4.539  1.00  8.21      1CRN 172\n" +
      "ATOM    104  N   VAL    15       4.993   2.927   2.571  1.00  3.76      1CRN 173\n" +
      "ATOM    105  CA  VAL    15       3.782   2.599   1.742  1.00  3.98      1CRN 174\n" +
      "ATOM    106  C   VAL    15       3.296   3.871   1.004  1.00  3.80      1CRN 175\n" +
      "ATOM    107  O   VAL    15       2.947   3.817   -.189  1.00  4.85      1CRN 176\n" +
      "ATOM    108  CB  VAL    15       2.698   1.953   2.608  1.00  4.71      1CRN 177\n" +
      "ATOM    109  CG1 VAL    15       1.384   1.826   1.806  1.00  6.67      1CRN 178\n" +
      "ATOM    110  CG2 VAL    15       3.174    .533   3.005  1.00  6.26      1CRN 179\n" +
      "ATOM    111  N   CYS    16       3.321   4.987   1.720  1.00  3.79      1CRN 180\n" +
      "ATOM    112  CA  CYS    16       2.890   6.285   1.126  1.00  3.54      1CRN 181\n" +
      "ATOM    113  C   CYS    16       3.687   6.597   -.111  1.00  3.48      1CRN 182\n" +
      "ATOM    114  O   CYS    16       3.200   7.147  -1.103  1.00  4.63      1CRN 183\n" +
      "ATOM    115  CB  CYS    16       3.039   7.369   2.240  1.00  4.58      1CRN 184\n" +
      "ATOM    116  SG  CYS    16       2.559   9.014   1.649  1.00  5.66      1CRN 185\n" +
      "ATOM    117  N   ARG    17       4.997   6.227   -.100  1.00  3.99      1CRN 186\n" +
      "ATOM    118  CA  ARG    17       5.895   6.489  -1.213  1.00  3.83      1CRN 187\n" +
      "ATOM    119  C   ARG    17       5.738   5.560  -2.409  1.00  3.79      1CRN 188\n" +
      "ATOM    120  O   ARG    17       6.228   5.901  -3.507  1.00  5.39      1CRN 189\n" +
      "ATOM    121  CB  ARG    17       7.370   6.507   -.731  1.00  4.11      1CRN 190\n" +
      "ATOM    122  CG  ARG    17       7.717   7.687    .206  1.00  4.69      1CRN 191\n" +
      "ATOM    123  CD  ARG    17       7.949   8.947   -.615  1.00  5.10      1CRN 192\n" +
      "ATOM    124  NE  ARG    17       9.212   8.856  -1.337  1.00  4.71      1CRN 193\n" +
      "ATOM    125  CZ  ARG    17       9.537   9.533  -2.431  1.00  5.28      1CRN 194\n" +
      "ATOM    126  NH1 ARG    17       8.659  10.350  -3.032  1.00  6.67      1CRN 195\n" +
      "ATOM    127  NH2 ARG    17      10.793   9.491  -2.899  1.00  6.41      1CRN 196\n" +
      "ATOM    128  N   LEU    18       5.051   4.411  -2.204  1.00  4.70      1CRN 197\n" +
      "ATOM    129  CA  LEU    18       4.933   3.431  -3.326  1.00  5.46      1CRN 198\n" +
      "ATOM    130  C   LEU    18       4.397   4.014  -4.620  1.00  5.13      1CRN 199\n" +
      "ATOM    131  O   LEU    18       4.988   3.755  -5.687  1.00  5.55      1CRN 200\n" +
      "ATOM    132  CB  LEU    18       4.196   2.184  -2.863  1.00  6.47      1CRN 201\n" +
      "ATOM    133  CG  LEU    18       4.960   1.178  -1.991  1.00  7.43      1CRN 202\n" +
      "ATOM    134  CD1 LEU    18       3.907    .097  -1.634  1.00  8.70      1CRN 203\n" +
      "ATOM    135  CD2 LEU    18       6.129    .606  -2.768  1.00  9.39      1CRN 204\n" +
      "ATOM    136  N   PRO    19       3.329   4.795  -4.543  1.00  4.28      1CRN 205\n" +
      "ATOM    137  CA  PRO    19       2.792   5.376  -5.797  1.00  5.38      1CRN 206\n" +
      "ATOM    138  C   PRO    19       3.573   6.540  -6.322  1.00  6.30      1CRN 207\n" +
      "ATOM    139  O   PRO    19       3.260   7.045  -7.422  1.00  9.62      1CRN 208\n" +
      "ATOM    140  CB  PRO    19       1.358   5.766  -5.472  1.00  5.87      1CRN 209\n" +
      "ATOM    141  CG  PRO    19       1.223   5.694  -3.993  1.00  6.47      1CRN 210\n" +
      "ATOM    142  CD  PRO    19       2.421   4.941  -3.408  1.00  6.45      1CRN 211\n" +
      "ATOM    143  N   GLY    20       4.565   7.047  -5.559  1.00  4.94      1CRN 212\n" +
      "ATOM    144  CA  GLY    20       5.366   8.191  -6.018  1.00  5.39      1CRN 213\n" +
      "ATOM    145  C   GLY    20       5.007   9.481  -5.280  1.00  5.03      1CRN 214\n" +
      "ATOM    146  O   GLY    20       5.535  10.510  -5.730  1.00  7.34      1CRN 215\n" +
      "ATOM    147  N   THR    21       4.181   9.438  -4.262  1.00  4.10      1CRN 216\n" +
      "ATOM    148  CA  THR    21       3.767  10.609  -3.513  1.00  3.94      1CRN 217\n" +
      "ATOM    149  C   THR    21       5.017  11.397  -3.042  1.00  3.96      1CRN 218\n" +
      "ATOM    150  O   THR    21       5.947  10.757  -2.523  1.00  5.82      1CRN 219\n" +
      "ATOM    151  CB  THR    21       2.992  10.188  -2.225  1.00  4.13      1CRN 220\n" +
      "ATOM    152  OG1 THR    21       2.051   9.144  -2.623  1.00  5.45      1CRN 221\n" +
      "ATOM    153  CG2 THR    21       2.260  11.349  -1.551  1.00  5.41      1CRN 222\n" +
      "ATOM    154  N   PRO    22       4.971  12.703  -3.176  1.00  5.04      1CRN 223\n" +
      "ATOM    155  CA  PRO    22       6.143  13.513  -2.696  1.00  4.69      1CRN 224\n" +
      "ATOM    156  C   PRO    22       6.400  13.233  -1.225  1.00  4.19      1CRN 225\n" +
      "ATOM    157  O   PRO    22       5.485  13.061   -.382  1.00  4.47      1CRN 226\n" +
      "ATOM    158  CB  PRO    22       5.703  14.969  -2.920  1.00  7.12      1CRN 227\n" +
      "ATOM    159  CG  PRO    22       4.676  14.893  -3.996  1.00  7.03      1CRN 228\n" +
      "ATOM    160  CD  PRO    22       3.964  13.567  -3.811  1.00  4.90      1CRN 229\n" +
      "ATOM    161  N   GLU    23       7.728  13.297   -.921  1.00  5.16      1CRN 230\n" +
      "ATOM    162  CA  GLU    23       8.114  13.103    .500  1.00  5.31      1CRN 231\n" +
      "ATOM    163  C   GLU    23       7.427  14.073   1.410  1.00  4.11      1CRN 232\n" +
      "ATOM    164  O   GLU    23       7.036  13.682   2.540  1.00  5.11      1CRN 233\n" +
      "ATOM    165  CB  GLU    23       9.648  13.285    .660  1.00  6.16      1CRN 234\n" +
      "ATOM    166  CG  GLU    23      10.440  12.093    .063  1.00  7.48      1CRN 235\n" +
      "ATOM    167  CD  GLU    23      11.941  12.170    .391  1.00  9.40      1CRN 236\n" +
      "ATOM    168  OE1 GLU    23      12.416  13.225    .681  1.00 10.40      1CRN 237\n" +
      "ATOM    169  OE2 GLU    23      12.539  11.070    .292  1.00 13.32      1CRN 238\n" +
      "ATOM    170  N   ALA    24       7.212  15.334    .966  1.00  4.56      1CRN 239\n" +
      "ATOM    171  CA  ALA    24       6.614  16.317   1.913  1.00  4.49      1CRN 240\n" +
      "ATOM    172  C   ALA    24       5.212  15.936   2.350  1.00  4.10      1CRN 241\n" +
      "ATOM    173  O   ALA    24       4.782  16.166   3.495  1.00  5.64      1CRN 242\n" +
      "ATOM    174  CB  ALA    24       6.605  17.695   1.246  1.00  5.80      1CRN 243\n" +
      "ATOM    175  N   ILE    25       4.445  15.318   1.405  1.00  4.37      1CRN 244\n" +
      "ATOM    176  CA  ILE    25       3.074  14.894   1.756  1.00  5.44      1CRN 245\n" +
      "ATOM    177  C   ILE    25       3.085  13.643   2.645  1.00  4.32      1CRN 246\n" +
      "ATOM    178  O   ILE    25       2.315  13.523   3.578  1.00  4.72      1CRN 247\n" +
      "ATOM    179  CB  ILE    25       2.204  14.637    .462  1.00  6.42      1CRN 248\n" +
      "ATOM    180  CG1 ILE    25       1.815  16.048   -.129  1.00  7.50      1CRN 249\n" +
      "ATOM    181  CG2 ILE    25        .903  13.864    .811  1.00  7.65      1CRN 250\n" +
      "ATOM    182  CD1 ILE    25        .756  16.761    .757  1.00  7.80      1CRN 251\n" +
      "ATOM    183  N   CYS    26       4.032  12.764   2.313  1.00  3.92      1CRN 252\n" +
      "ATOM    184  CA  CYS    26       4.180  11.549   3.187  1.00  4.37      1CRN 253\n" +
      "ATOM    185  C   CYS    26       4.632  11.944   4.596  1.00  3.95      1CRN 254\n" +
      "ATOM    186  O   CYS    26       4.227  11.252   5.547  1.00  4.74      1CRN 255\n" +
      "ATOM    187  CB  CYS    26       5.038  10.518   2.539  1.00  4.63      1CRN 256\n" +
      "ATOM    188  SG  CYS    26       4.349   9.794   1.022  1.00  5.61      1CRN 257\n" +
      "ATOM    189  N   ALA    27       5.408  13.012   4.694  1.00  3.89      1CRN 258\n" +
      "ATOM    190  CA  ALA    27       5.879  13.502   6.026  1.00  4.43      1CRN 259\n" +
      "ATOM    191  C   ALA    27       4.696  13.908   6.882  1.00  4.26      1CRN 260\n" +
      "ATOM    192  O   ALA    27       4.528  13.422   8.025  1.00  5.44      1CRN 261\n" +
      "ATOM    193  CB  ALA    27       6.880  14.615   5.830  1.00  5.36      1CRN 262\n" +
      "ATOM    194  N   THR    28       3.827  14.802   6.358  1.00  4.53      1CRN 263\n" +
      "ATOM    195  CA  THR    28       2.691  15.221   7.194  1.00  5.08      1CRN 264\n" +
      "ATOM    196  C   THR    28       1.672  14.132   7.434  1.00  4.62      1CRN 265\n" +
      "ATOM    197  O   THR    28        .947  14.112   8.468  1.00  7.80      1CRN 266\n" +
      "ATOM    198  CB  THR    28       1.986  16.520   6.614  1.00  6.03      1CRN 267\n" +
      "ATOM    199  OG1 THR    28       1.664  16.221   5.230  1.00  7.19      1CRN 268\n" +
      "ATOM    200  CG2 THR    28       2.914  17.739   6.700  1.00  7.34      1CRN 269\n" +
      "ATOM    201  N   TYR    29       1.621  13.190   6.511  1.00  5.01      1CRN 270\n" +
      "ATOM    202  CA  TYR    29        .715  12.045   6.657  1.00  6.60      1CRN 271\n" +
      "ATOM    203  C   TYR    29       1.125  11.125   7.815  1.00  4.92      1CRN 272\n" +
      "ATOM    204  O   TYR    29        .286  10.632   8.545  1.00  7.13      1CRN 273\n" +
      "ATOM    205  CB  TYR    29        .755  11.229   5.322  1.00  9.66      1CRN 274\n" +
      "ATOM    206  CG  TYR    29       -.203  10.044   5.354  1.00 11.56      1CRN 275\n" +
      "ATOM    207  CD1 TYR    29      -1.547  10.337   5.645  1.00 12.85      1CRN 276\n" +
      "ATOM    208  CD2 TYR    29        .193   8.750   5.100  1.00 14.44      1CRN 277\n" +
      "ATOM    209  CE1 TYR    29      -2.496   9.329   5.673  1.00 16.61      1CRN 278\n" +
      "ATOM    210  CE2 TYR    29       -.801   7.705   5.156  1.00 17.11      1CRN 279\n" +
      "ATOM    211  CZ  TYR    29      -2.079   8.031   5.430  1.00 19.99      1CRN 280\n" +
      "ATOM    212  OH  TYR    29      -3.097   7.057   5.458  1.00 28.98      1CRN 281\n" +
      "ATOM    213  N   THR    30       2.470  10.984   7.995  1.00  5.31      1CRN 282\n" +
      "ATOM    214  CA  THR    30       2.986   9.994   8.950  1.00  5.70      1CRN 283\n" +
      "ATOM    215  C   THR    30       3.609  10.505  10.230  1.00  6.28      1CRN 284\n" +
      "ATOM    216  O   THR    30       3.766   9.715  11.186  1.00  8.77      1CRN 285\n" +
      "ATOM    217  CB  THR    30       4.076   9.103   8.225  1.00  6.55      1CRN 286\n" +
      "ATOM    218  OG1 THR    30       5.125  10.027   7.824  1.00  6.57      1CRN 287\n" +
      "ATOM    219  CG2 THR    30       3.493   8.324   7.035  1.00  7.29      1CRN 288\n" +
      "ATOM    220  N   GLY    31       3.984  11.764  10.241  1.00  4.99      1CRN 289\n" +
      "ATOM    221  CA  GLY    31       4.769  12.336  11.360  1.00  5.50      1CRN 290\n" +
      "ATOM    222  C   GLY    31       6.255  12.243  11.106  1.00  4.19      1CRN 291\n" +
      "ATOM    223  O   GLY    31       7.037  12.750  11.954  1.00  6.12      1CRN 292\n" +
      "ATOM    224  N   CYS    32       6.710  11.631   9.992  1.00  4.30      1CRN 293\n" +
      "ATOM    225  CA  CYS    32       8.140  11.694   9.635  1.00  4.89      1CRN 294\n" +
      "ATOM    226  C   CYS    32       8.500  13.141   9.206  1.00  5.50      1CRN 295\n" +
      "ATOM    227  O   CYS    32       7.581  13.949   8.944  1.00  5.82      1CRN 296\n" +
      "ATOM    228  CB  CYS    32       8.504  10.686   8.530  1.00  4.66      1CRN 297\n" +
      "ATOM    229  SG  CYS    32       8.048   8.987   8.881  1.00  5.33      1CRN 298\n" +
      "ATOM    230  N   ILE    33       9.793  13.410   9.173  1.00  6.02      1CRN 299\n" +
      "ATOM    231  CA  ILE    33      10.280  14.760   8.823  1.00  5.24      1CRN 300\n" +
      "ATOM    232  C   ILE    33      11.346  14.658   7.743  1.00  5.16      1CRN 301\n" +
      "ATOM    233  O   ILE    33      11.971  13.583   7.552  1.00  7.19      1CRN 302\n" +
      "ATOM    234  CB  ILE    33      10.790  15.535  10.085  1.00  5.49      1CRN 303\n" +
      "ATOM    235  CG1 ILE    33      12.059  14.803  10.671  1.00  6.85      1CRN 304\n" +
      "ATOM    236  CG2 ILE    33       9.684  15.686  11.138  1.00  6.45      1CRN 305\n" +
      "ATOM    237  CD1 ILE    33      12.733  15.676  11.781  1.00  8.94      1CRN 306\n" +
      "ATOM    238  N   ILE    34      11.490  15.773   7.038  1.00  5.52      1CRN 307\n" +
      "ATOM    239  CA  ILE    34      12.552  15.877   6.036  1.00  6.82      1CRN 308\n" +
      "ATOM    240  C   ILE    34      13.590  16.917   6.560  1.00  6.92      1CRN 309\n" +
      "ATOM    241  O   ILE    34      13.168  18.006   6.945  1.00  9.22      1CRN 310\n" +
      "ATOM    242  CB  ILE    34      11.987  16.360   4.681  1.00  8.11      1CRN 311\n" +
      "ATOM    243  CG1 ILE    34      10.914  15.338   4.163  1.00  9.59      1CRN 312\n" +
      "ATOM    244  CG2 ILE    34      13.131  16.517   3.629  1.00  9.73      1CRN 313\n" +
      "ATOM    245  CD1 ILE    34      10.151  16.024   2.938  1.00 13.41      1CRN 314\n" +
      "ATOM    246  N   ILE    35      14.856  16.493   6.536  1.00  7.06      1CRN 315\n" +
      "ATOM    247  CA  ILE    35      15.930  17.454   6.941  1.00  7.52      1CRN 316\n" +
      "ATOM    248  C   ILE    35      16.913  17.550   5.819  1.00  6.63      1CRN 317\n" +
      "ATOM    249  O   ILE    35      17.097  16.660   4.970  1.00  7.90      1CRN 318\n" +
      "ATOM    250  CB  ILE    35      16.622  16.995   8.285  1.00  8.07      1CRN 319\n" +
      "ATOM    251  CG1 ILE    35      17.360  15.651   8.067  1.00  9.41      1CRN 320\n" +
      "ATOM    252  CG2 ILE    35      15.592  16.974   9.434  1.00  9.46      1CRN 321\n" +
      "ATOM    253  CD1 ILE    35      18.298  15.206   9.219  1.00  9.85      1CRN 322\n" +
      "ATOM    254  N   PRO    36      17.664  18.669   5.806  1.00  8.07      1CRN 323\n" +
      "ATOM    255  CA  PRO    36      18.635  18.861   4.738  1.00  8.78      1CRN 324\n" +
      "ATOM    256  C   PRO    36      19.925  18.042   4.949  1.00  8.31      1CRN 325\n" +
      "ATOM    257  O   PRO    36      20.593  17.742   3.945  1.00  9.09      1CRN 326\n" +
      "ATOM    258  CB  PRO    36      18.945  20.364   4.783  1.00  9.67      1CRN 327\n" +
      "ATOM    259  CG  PRO    36      18.238  20.937   5.908  1.00 10.15      1CRN 328\n" +
      "ATOM    260  CD  PRO    36      17.371  19.900   6.596  1.00  9.53      1CRN 329\n" +
      "ATOM    261  N   GLY    37      20.172  17.730   6.217  1.00  8.48      1CRN 330\n" +
      "ATOM    262  CA  GLY    37      21.452  16.969   6.513  1.00  9.20      1CRN 331\n" +
      "ATOM    263  C   GLY    37      21.143  15.478   6.427  1.00 10.41      1CRN 332\n" +
      "ATOM    264  O   GLY    37      20.138  15.023   5.878  1.00 12.06      1CRN 333\n" +
      "ATOM    265  N   ALA    38      22.055  14.701   7.032  1.00  9.24      1CRN 334\n" +
      "ATOM    266  CA  ALA    38      22.019  13.242   7.020  1.00  9.24      1CRN 335\n" +
      "ATOM    267  C   ALA    38      21.944  12.628   8.396  1.00  9.60      1CRN 336\n" +
      "ATOM    268  O   ALA    38      21.869  11.387   8.435  1.00 13.65      1CRN 337\n" +
      "ATOM    269  CB  ALA    38      23.246  12.697   6.275  1.00 10.43      1CRN 338\n" +
      "ATOM    270  N   THR    39      21.894  13.435   9.436  1.00  8.70      1CRN 339\n" +
      "ATOM    271  CA  THR    39      21.936  12.911  10.809  1.00  9.46      1CRN 340\n" +
      "ATOM    272  C   THR    39      20.615  13.191  11.521  1.00  8.32      1CRN 341\n" +
      "ATOM    273  O   THR    39      20.357  14.317  11.948  1.00  9.89      1CRN 342\n" +
      "ATOM    274  CB  THR    39      23.131  13.601  11.593  1.00 10.72      1CRN 343\n" +
      "ATOM    275  OG1 THR    39      24.284  13.401  10.709  1.00 11.66      1CRN 344\n" +
      "ATOM    276  CG2 THR    39      23.340  12.935  12.962  1.00 11.81      1CRN 345\n" +
      "ATOM    277  N   CYS    40      19.827  12.110  11.642  1.00  7.64      1CRN 346\n" +
      "ATOM    278  CA  CYS    40      18.504  12.312  12.298  1.00  8.05      1CRN 347\n" +
      "ATOM    279  C   CYS    40      18.684  12.451  13.784  1.00  7.63      1CRN 348\n" +
      "ATOM    280  O   CYS    40      19.533  11.718  14.362  1.00  9.64      1CRN 349\n" +
      "ATOM    281  CB  CYS    40      17.582  11.117  11.996  1.00  7.80      1CRN 350\n" +
      "ATOM    282  SG  CYS    40      17.199  10.929  10.237  1.00  7.30      1CRN 351\n" +
      "ATOM    283  N   PRO    41      17.880  13.266  14.426  1.00  8.00      1CRN 352\n" +
      "ATOM    284  CA  PRO    41      17.924  13.421  15.877  1.00  8.96      1CRN 353\n" +
      "ATOM    285  C   PRO    41      17.392  12.206  16.594  1.00  9.06      1CRN 354\n" +
      "ATOM    286  O   PRO    41      16.652  11.368  16.033  1.00  8.82      1CRN 355\n" +
      "ATOM    287  CB  PRO    41      17.076  14.658  16.145  1.00 10.39      1CRN 356\n" +
      "ATOM    288  CG  PRO    41      16.098  14.689  14.997  1.00 10.99      1CRN 357\n" +
      "ATOM    289  CD  PRO    41      16.859  14.150  13.779  1.00 10.49      1CRN 358\n" +
      "ATOM    290  N   GLY    42      17.728  12.124  17.884  1.00  7.55      1CRN 359\n" +
      "ATOM    291  CA  GLY    42      17.334  10.956  18.691  1.00  8.00      1CRN 360\n" +
      "ATOM    292  C   GLY    42      15.875  10.688  18.871  1.00  7.22      1CRN 361\n" +
      "ATOM    293  O   GLY    42      15.434   9.550  19.166  1.00  8.41      1CRN 362\n" +
      "ATOM    294  N   ASP    43      15.036  11.747  18.715  1.00  5.54      1CRN 363\n" +
      "ATOM    295  CA  ASP    43      13.564  11.573  18.836  1.00  5.85      1CRN 364\n" +
      "ATOM    296  C   ASP    43      12.936  11.227  17.470  1.00  5.87      1CRN 365\n" +
      "ATOM    297  O   ASP    43      11.720  11.040  17.428  1.00  7.29      1CRN 366\n" +
      "ATOM    298  CB  ASP    43      12.933  12.737  19.580  1.00  6.72      1CRN 367\n" +
      "ATOM    299  CG  ASP    43      13.140  14.094  18.958  1.00  8.59      1CRN 368\n" +
      "ATOM    300  OD1 ASP    43      14.109  14.303  18.212  1.00  9.59      1CRN 369\n" +
      "ATOM    301  OD2 ASP    43      12.267  14.963  19.265  1.00 11.45      1CRN 370\n" +
      "ATOM    302  N   TYR    44      13.725  11.174  16.425  1.00  5.22      1CRN 371\n" +
      "ATOM    303  CA  TYR    44      13.257  10.745  15.081  1.00  5.56      1CRN 372\n" +
      "ATOM    304  C   TYR    44      14.275   9.687  14.612  1.00  4.61      1CRN 373\n" +
      "ATOM    305  O   TYR    44      14.930   9.862  13.568  1.00  6.04      1CRN 374\n" +
      "ATOM    306  CB  TYR    44      13.200  11.914  14.071  1.00  5.41      1CRN 375\n" +
      "ATOM    307  CG  TYR    44      12.000  12.819  14.399  1.00  5.34      1CRN 376\n" +
      "ATOM    308  CD1 TYR    44      12.119  13.853  15.332  1.00  6.59      1CRN 377\n" +
      "ATOM    309  CD2 TYR    44      10.775  12.617  13.762  1.00  5.94      1CRN 378\n" +
      "ATOM    310  CE1 TYR    44      11.045  14.675  15.610  1.00  5.97      1CRN 379\n" +
      "ATOM    311  CE2 TYR    44       9.676  13.433  14.048  1.00  5.17      1CRN 380\n" +
      "ATOM    312  CZ  TYR    44       9.802  14.456  14.996  1.00  5.96      1CRN 381\n" +
      "ATOM    313  OH  TYR    44       8.740  15.265  15.269  1.00  8.60      1CRN 382\n" +
      "ATOM    314  N   ALA    45      14.342   8.640  15.422  1.00  4.76      1CRN 383\n" +
      "ATOM    315  CA  ALA    45      15.445   7.667  15.246  1.00  5.89      1CRN 384\n" +
      "ATOM    316  C   ALA    45      15.171   6.533  14.280  1.00  6.67      1CRN 385\n" +
      "ATOM    317  O   ALA    45      16.093   5.705  14.039  1.00  7.56      1CRN 386\n" +
      "ATOM    318  CB  ALA    45      15.680   7.099  16.682  1.00  6.82      1CRN 387\n" +
      "ATOM    319  N   ASN    46      13.966   6.502  13.739  1.00  5.80      1CRN 388\n" +
      "ATOM    320  CA  ASN    46      13.512   5.395  12.878  1.00  6.15      1CRN 389\n" +
      "ATOM    321  C   ASN    46      13.311   5.853  11.455  1.00  6.61      1CRN 390\n" +
      "ATOM    322  O   ASN    46      13.733   6.929  11.026  1.00  7.18      1CRN 391\n" +
      "ATOM    323  CB  ASN    46      12.266   4.769  13.501  1.00  7.27      1CRN 392\n" +
      "ATOM    324  CG  ASN    46      12.538   4.304  14.922  1.00  7.98      1CRN 393\n" +
      "ATOM    325  OD1 ASN    46      11.982   4.849  15.886  1.00 11.00      1CRN 394\n" +
      "ATOM    326  ND2 ASN    46      13.407   3.298  15.015  1.00 10.32      1CRN 395\n" +
      "ATOM    327  OXT ASN    46      12.703   4.973  10.746  1.00  7.86      1CRN 396\n" +
      "TER     328      ASN    46                                              1CRN 397\n" +
      "CONECT   20   19  282                                                   1CRN 398\n" +
      "CONECT   26   25  229                                                   1CRN 399\n" +
      "CONECT  116  115  188                                                   1CRN 400\n" +
      "CONECT  188  116  187                                                   1CRN 401\n" +
      "CONECT  229   26  228                                                   1CRN 402\n" +
      "CONECT  282   20  281                                                   1CRN 403\n" +
      "MASTER       62    0    0    2    2    1    0    6  327    1    6    4  1CRND  6\n" +
      "END                                                                     1CRN 405";


}
