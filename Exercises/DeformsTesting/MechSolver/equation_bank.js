const equations = [
    {
        group: 'Axial',
        id: "axialStress",
        name: 'Stress in an axial',
        latex: '\\delta_t = \\alpha \\cdot \\Delta T \\cdot L',
        latex_boxes: '\\Box=\\Box\\times\\Box\\times\\Box',
        params_latex: ['\\delta_t', '\\alpha', '\\Delta T', 'L'],
        template: 'deform = const * tempchange * len',
        params: ['deform', 'const', 'tempchange', 'len'],
        variables: {
            'deform':'\\delta_t',
            'const':'\\alpha',
            'tempchange':'\\Delta T',
            'len':'L'
        },
        domains: {
            'deform':'length',
            'const':'temperature-1',
            'tempchange':'temperature',
            'len':'length'
        }
    },
    {
        group: 'Basic definitions',
        id: "axialMemberAvgNormalStress",
        name: 'Average normal stress in an axial member',
        latex: '\\delta_{avg}=\\frac F A',
        latex_boxes: '\\Box=\\frac \\Box \\Box',
        params_latex: ['\\delta_{avg}', 'A', 'F'],
        template: 'avgdeform = force / area',
        params: ['avgdeform', 'area', 'force'],
        variables: {
            'avgdeform': '\\delta_{avg}',
            'force': 'F',
            'area': 'A'
        },
        domains: {
            'avgdeform': 'length',
            'force': 'force',
            'area': 'length2'
        }
    },
    {
        group: 'Basic definitions',
        id: "dirShearStressAvg",
        name: 'Average direct shear stress',
        latex: '\\tau_{avg}=\\frac V {A_v}',
        latex_boxes: '\\Box=\\frac \\Box \\Box',
        params_latex: ['\\tau_{avg}', 'V', 'A_v'],
        template: 'stress = volume / areaV',
        params: ['stress', 'areaV', 'volume'],
        variables: {
            'avgstress': '\\tau_{avg}',
            'volume': 'V',
            'areaV': '{A_v}'
        },
        domains: {
            'avgstress': 'pressure',
            'volume': 'length3',
            'areaV': 'length2'
        }
    },
    {
        group: 'Axial',
        id: "shearStrainMaxInPlane1",
        name: 'Maximum in-plane shear strain',
        latex: '\\gamma_{max}=\\pm2\\sqrt{\\left(\\frac{\\varepsilon_x - \\varepsilon_y}{2}\\right)^2+\\left(\\frac{\\gamma_{xy}}{2}\\right)^2}',
        latex_boxes: '\\Box=\\pm2\\sqrt{\\left(\\frac{\\Box - \\Box}{2}\\right)^2+\\left(\\frac{\\Box}{2}\\right)^2}',
        params_latex: ['\\gamma_{max}', '\\varepsilon_x', '\\varepsilon_y', '\\gamma_{xy}'],
        template: 'maxshear=2*sqrt( (epsX - epsY/2)^2 + (shear/2)^2 )',
        params: ['maxshear', 'epsX', 'epsY', 'shear'],
        variables: {
            'maxshear': '\\gamma_{max}',
            'epsX': '\\varepsilon_x',
            'epsY': '\\varepsilon_y',
            'shear': '\\gamma_{xy}',
        },
        domains: {
            'maxshear': 'coefficient',
            'epsX': 'coefficient',
            'epsY': 'coefficient',
            'shear': 'coefficient',
        }
    },
    {
        group: 'Axial',
        id: "Relating deformation to stress L and E",
        name: 'Intermediate stress equation',
        latex: '\\delta=\\sigma \\frac L E',
        latex_boxes: '\\Box=\\Box \\frac \\Box \\Box',
        params_latex: ['\\delta','\\sigma', 'L', 'E'],
        template: 'deform = stress * length / pressE',
        params: ['deform', 'stress', 'pressE', 'length'],
        variables: {
            'deform': '\\delta',
            'stress': '\\sigma',
            'pressE': 'E',
            'length': 'L'
        },
        domains: {
            'deform': 'length',
            'stress': 'pressure',
            'pressE': 'pressure',
            'length': 'length'
        }
    },
    // {
    //     group: 'Axial',
    //     id: "deformationToStressLEdemo",
    //     name: 'Intermediate stress equation',
    //     latex: '45.856=45.856 \\frac {45.856} {45.856}',
    //     latex_boxes: '\\Box=\\Box \\frac \\Box \\Box',
    //     params_latex: ['\\delta','\\sigma', 'L', 'E'],
    //     template: 'deform = stress * length / pressE',
    //     params: ['deform', 'stress', 'pressE', 'length'],
    //     variables: {
    //         'deform': '\\delta',
    //         'stress': '\\sigma',
    //         'pressE': 'E',
    //         'length': 'L'
    //     }
    // },
    
    // {
    //     group: 'Axial',
    //     name: 'Maximum in-plane shear strain',
    //     id: "shearStrainMaxInPlane2",
    //     latex: '\\gamma_{max}=\\pm\\sqrt{(\\varepsilon_x-\\varepsilon_y)^2+\\gamma_{xy}^2}',
    //     latex_boxes: '\\Box=\\pm\\sqrt{(\\Box-\\Box)^2+\\Box^2}',
    //     params: ['\\gamma_{max}', '\\varepsilon_x', '\\varepsilon_y', '\\gamma_{xy}']
    // },
    // {
    //     group: 'Basic definitions',
    //     id: "bearingStressAvg",
    //     name: 'Average bearing stress',
    //     latex: '\\delta_b=\\frac V {A_b}',
    //     latex_boxes: '\\Box=\\frac \\Box {\\Box}',
    //     params: ['\\delta_b', 'V', 'A_b']
    // },
    // {
    //     group: 'Basic definitions',
    //     id: "axialMemberAvgNormalStrain1",
    //     name: 'Average normal strain in an axial member',
    //     latex: '\\varepsilon_{long}=\\frac{\\Delta L} L',
    //     latex_boxes: '\\Box=\\frac{\\Box} \\Box',
    //     params: []
    // },
    // {
    //     group: 'Basic definitions',
    //     id: "axialMemberAvgNormalStrain2",
    //     name: 'Average normal strain in an axial member',
    //     latex: '\\varepsilon_{lat}=\\frac{\\Delta d} d',
    //     latex_boxes: '\\\\Box=\\frac{\\Box \\Box} \\Box',
    //     params: []
    // },
    // {
    //     group: 'Basic definitions',
    //     id: "tempChangeAvgNormalStrain",
    //     name: 'Average normal strain caused by temperature change',
    //     latex: '\\varepsilon_T=\\alpha \\cdot \\Delta L',
    //     latex_boxes: '\\Box=\\Box \\times \\Box',
    //     params: []
    // },
    // {
    //     group: 'Basic definitions',
    //     id: "relnEGV",
    //     name: 'Relationship between E, G, and ν',
    //     latex: 'G=\\frac E{2(1+\\nu)}',
    //     latex_boxes: '\\Box=\\frac \\Box{2(1+\\Box)}',
    //     params: []
    // },
    // {
    //     group: 'Basic definitions',
    //     id: "safetyFactor",
    //     name: 'Factor of safety',
    //     latex: '{FS}=\\frac{\\delta_{failure}}{\\delta_{actual}}',
    //     latex_boxes: '{\\Box}=\\frac{\\Box}{\\Box}',
    //     params: []
    // },
    // {
    //     group: 'Axial deformation',
    //     id: "RelnForceTempDef",
    //     name: 'Force-temperature-deformation relationship',
    //     latex: '\\delta=\\frac{F\\cdot L}{A\\cdot E} + \\alpha \\cdot \\Delta T\\cdot L',
    //     latex_boxes: '\\Box=\\frac{\\Box\\times\\Box}{\\Box\\times\\Box} + \\Box \\times \\Box\\times \\Box',
    //     params: ['\\delta', 'F', 'L', 'A', 'E', 'alpha', '\\Delta T', 'L']
    // },
    // {
    //     group: 'Torsion',
    //     id: "circShaftMaxTorsionShearStress",
    //     name: 'Maximum torsion shear stress in a circular shaft',
    //     latex: '\\tau_{max}=\\frac{T_c}J',
    //     latex_boxes: '\\Box=\\frac \\Box \\Box',
    //     params: []
    // },
    // {
    //     group: 'Torsion',
    //     id: "circShaftMaxTorsionShearStress-J",
    //     name: 'J in Maximum torsion shear stress in a circular shaft',
    //     latex: 'J=\\frac\\pi 2 [R^4-r^4]',
    //     latex_boxes: '\\Box=\\frac\\pi 2 [\\Box^4-\\Box^4]',
    //     params: ['J', 'R', 'r']
    // },
    {
        group: 'Arithmetic',
        id: "add2",
        name: 'Addition of 2 terms',
        latex: 'c=a+b',
        latex_boxes: '\\Box=\\Box+\\Box',
        params_latex: ['c','b', 'a'],
        template: 'cterm = aterm + bterm',
        params: ['cterm', 'bterm', 'aterm'],
        variables: {
            'cterm': 'c',
            'bterm': 'b',
            'aterm': 'a'
        },
        domains: {
            'cterm': 'dimensionless',
            'bterm': 'dimensionless',
            'aterm': 'dimensionless'
        }
    },
    {
        group: 'Arithmetic',
        id: "add3",
        name: 'Addition of 3 terms',
        latex: 'c=x+y+z',
        latex_boxes: '\\Box=\\Box+\\Box+\\Box',
        params_latex: ['c', 'x', 'y', 'z'],
        template: 'cterm = xterm + yterm + zterm',
        params: ['cterm', 'xterm', 'yterm', 'zterm'],
        variables: {
            'cterm': 'c',
            'xterm': 'x',
            'yterm': 'y',
            'zterm': 'z'
        },
        domains: {
            'cterm': 'dimensionless',
            'xterm': 'dimensionless',
            'yterm': 'dimensionless',
            'zterm': 'dimensionless'
        }
    },
    {
        group: 'Arithmetic',
        id: "sub",
        name: 'Subtraction',
        latex: 'c=a-b',
        latex_boxes: '\\Box=\\Box-\\Box',
        params_latex: ['c','b', 'a'],
        template: 'cterm = bterm - aterm',
        params: ['cterm', 'bterm', 'aterm'],
        variables: {
            'cterm': 'c',
            'bterm': 'b',
            'aterm': 'a'
        },
        domains: {
            'cterm': 'dimensionless',
            'bterm': 'dimensionless',
            'aterm': 'dimensionless'
        }
    },
    {
        group: 'Arithmetic',
        id: "div",
        name: 'Division',
        latex: 'c=\\frac{a}{b}',
        latex_boxes: '\\Box=\\frac{\\Box}{\\Box}',
        params_latex: ['c','b', 'a'],
        template: 'cterm = aterm  bterm',
        params: ['cterm', 'bterm', 'aterm'],
        variables: {
            'cterm': 'c',
            'bterm': 'b',
            'aterm': 'a'
        },
        domains: {
            'cterm': 'dimensionless',
            'bterm': 'dimensionless',
            'aterm': 'dimensionless'
        }
    },
    {
        group: 'Arithmetic',
        id: "mult",
        name: 'Multiplication',
        latex: 'c=a \\times b',
        latex_boxes: '\\Box=\\Box\\times\\Box',
        params_latex: ['c','b', 'a'],
        template: 'cterm = aterm * bterm',
        params: ['cterm', 'aterm', 'bterm'],
        variables: {
            'cterm': 'c',
            'bterm': 'b',
            'aterm': 'a'
        },
        domains: {
            'cterm': 'dimensionless',
            'bterm': 'dimensionless',
            'aterm': 'dimensionless'
        }
    },
]