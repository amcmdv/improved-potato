const biojava = require('biojava'); // Pseudo-library for BioJava integration
const thermodynamics = require('./thermodynamics'); // Custom thermodynamic analysis module

/**
 * Detect mutations with a focus on temperature-sensitive regions.
 * @param {string} reference - The reference DNA sequence.
 * @param {string} sample - The sample DNA sequence.
 * @param {number} temperature - The temperature at which the analysis is conducted.
 * @returns {Object} - Detailed mutation analysis including thermodynamic impact.
 */
function detectMutations(reference, sample, temperature) {
    if (reference.length !== sample.length) {
        throw new Error("Sequences must be of the same length.");
    }

    let mutations = [];
    for (let i = 0; i < reference.length; i++) {
        const refChar = reference[i];
        const sampleChar = sample[i];

        if (refChar !== sampleChar) {
            const thermodynamicImpact = thermodynamics.analyseMutation(refChar, sampleChar, temperature);
            mutations.push({
                position: i + 1,
                original: refChar,
                mutated: sampleChar,
                impact: thermodynamicImpact,
            });
        }
    }

    return mutations.length > 0 ? mutations : "No significant mutations detected.";
}

module.exports = { detectMutations };

