package com.company;

public class Matrix {
    private int size;
    private Complex[][] matrix;

    public int getSize() {
        return size;
    }

    public Complex[][] getMatrix() {
        return matrix;
    }

    public Matrix(int size) {
        this.size = size;
        this.matrix = new Complex[size][size];
    }

    public boolean isCorrect(int size_data) {
        if (this.size != size_data) {
            System.out.println("Different matrix sizes");
            return false;
        }
        return true;
    }

    public void print(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public void setMatrix(Complex[][] new_matrix) {
        if (!isCorrect(new_matrix.length)) return;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new Complex(new_matrix[i][j].getReal(), new_matrix[i][j].getImage());
            }
        }
    }

    public Matrix sum(Matrix b) throws ArrayIndexOutOfBoundsException{
        if (!this.isCorrect(b.getSize())) return null;

        Complex[][] data = new Complex[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = matrix[i][j].sum(b.matrix[i][j]);
            }
        }
        Matrix new_Matrix = new Matrix(size);
        new_Matrix.setMatrix(data);
        return new_Matrix;
    }

    public Matrix sub(Matrix b) throws ArrayIndexOutOfBoundsException{
        if (!this.isCorrect(b.getSize())) return null;

        Complex[][] data = new Complex[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = matrix[i][j].sub(b.matrix[i][j]);
            }
        }
        Matrix new_Matrix = new Matrix(size);
        new_Matrix.setMatrix(data);
        return new_Matrix;
    }

    public Matrix mul(Matrix b) throws ArrayIndexOutOfBoundsException{
        if (!this.isCorrect(b.getSize())) return null;


        Complex[][] data = new Complex[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Complex elem_ij = new Complex();
                for (int k = 0; k < size; k++){
                    elem_ij = elem_ij.sum(matrix[i][k].mul(b.matrix[k][j]));
                }
                data[i][j] = elem_ij;
            }
        }
        Matrix new_Matrix = new Matrix(size);
        new_Matrix.setMatrix(data);
        return new_Matrix;
    }

    public Matrix transponse() throws ArrayIndexOutOfBoundsException{

        Complex[][] data = new Complex[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                data[i][j] = matrix[j][i];
            }
        }

        Matrix new_Matrix = new Matrix(size);
        new_Matrix.setMatrix(data);
        return new_Matrix;
    }

    public Complex determinate(Complex[][] matrix) throws ArrayIndexOutOfBoundsException{

        Complex calcResult = new Complex();
        if (matrix.length == 1) {
            calcResult = matrix[0][0];
        }
        if (matrix.length==2){
            calcResult = (matrix[0][0].mul(matrix[1][1])).sub(matrix[1][0].mul(matrix[0][1]));
        }
        else
        /*{
            calcResult = (matrix[0][0].mul(matrix[1][1].mul(matrix[2][2]))).sum(matrix[0][1].mul(matrix[1][2].mul(matrix[2][0])))
                         .sum(matrix[0][2].mul(matrix[1][0].mul(matrix[2][1]))).sub(matrix[0][2].mul(matrix[1][1].mul(matrix[2][0])))
                                 .sub(matrix[0][1].mul(matrix[1][0].mul(matrix[2][2]))).sub(matrix[0][0].mul(matrix[1][2].mul(matrix[2][1])));
        }*/ {
            for (int i = 0; i < matrix[0].length; i++) {
                Complex[][] temp = new Complex[matrix.length - 1][matrix[0].length - 1];
                for (int j = 1; j < matrix.length; j++) {
                    for (int k = 0; k < matrix[0].length; k++) {

                        if (k < i) {
                            temp[j - 1][k] = matrix[j][k];
                        } else if (k > i) {
                            temp[j - 1][k - 1] = matrix[j][k];
                        }
                    }
                }
                Complex minor = determinate(temp).mul(matrix[0][i].mul(new Complex(Math.pow(-1, i))));
                calcResult = calcResult.sum(minor);
            }
        }
        return calcResult;

    }
    public Complex determinate() throws ArrayIndexOutOfBoundsException  {
        return this.determinate(matrix);
    }


}
