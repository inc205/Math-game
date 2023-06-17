interface OperationParameters {
  units: number;
  rows: number;
}

class Operations {
  /**
   * @param OperationParameters
   *  - @param units: Define number length
   *  - @param rows Define numbers quantity
   */
  //SUM
  sum({ units, rows }: OperationParameters) {
    if (this.validation({ units, rows })) return;
    const digits: number[] = this.getDigits({ units, rows });
    const result: number = digits.reduce(
      (current, total) => current + total,
      0
    );
    this.logResults(digits, result);
  }

  //SUBTRACTION
  reduce({ units, rows }: OperationParameters) {
    if (this.validation({ units, rows })) return;
    const digits: number[] = this.getDigits({ units, rows });
    const result: number = digits.reduce((current, total) => current - total);
    this.logResults(digits, result);
  }

  //MULTIPLICATION
  mult({ units, rows }: OperationParameters) {
    if (this.validation({ units, rows })) return;
    const digits: number[] = this.getDigits({ units, rows });
    const result: number = digits.reduce(
      (current, total) => current * total,
      1
    );
    this.logResults(digits, result);
  }

  //DIVISION
  division({ units, rows }: OperationParameters) {
    if (this.validation({ units, rows })) return;
    const digits: number[] = this.getDigits({ units, rows });
    const result: string = digits
      .reduce((current, total) => current / total)
      .toFixed(2);
    this.logResults(digits, result);
  }

  private logResults(digits: number[], result: number | string) {
    console.log(digits);
    console.log(result);
  }

  private validation({ units, rows }: OperationParameters) {
    if (units <= 0 || rows <= 0) {
      console.log('Insert valid values');
      return true;
    }
    return false;
  }

  private getDigits({ units, rows }: OperationParameters) {
    const numbers: number[] = [];
    // get numbers quiantity
    for (let i = 0; i < rows; i++) {
      // get number with designed length
      for (let j = 0, number = ''; j < units; j++) {
        number = number.concat(Math.ceil(Math.random() * 9).toString());
        number.length === units ? numbers.push(Number(number)) : null;
      }
    }
    return numbers;
  }
}

const operations = new Operations();

operations.sum({ units: 2, rows: 2 });
operations.reduce({ units: 4, rows: 5 });
operations.mult({ units: 2, rows: 2 });
operations.division({ units: 1, rows: 2 });
