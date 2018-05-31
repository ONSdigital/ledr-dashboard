/**
 * Used instead of Math.round() as that can have rounding errors due to floating-number arithmetic.
 * Source: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/round
 * @param number
 * @param precision
 * @returns number
 */
export const round = (number, precision) => {
  let shift = function (number, precision) {
    let numArray = ("" + number).split("e");
    return +(numArray[0] + "e" + (numArray[1] ? (+numArray[1] + precision) : precision));
  };

  let finalValue = shift(Math.round(shift(number, +precision)), -precision);

  if (isNaN(finalValue)) {
    finalValue = 0;
  }

  return finalValue;
};


export const toTitleCase = (str) => {
  return str.replace(/\w\S*/g, function (txt) {
    return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
  });
};